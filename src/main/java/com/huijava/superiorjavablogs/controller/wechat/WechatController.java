package com.huijava.superiorjavablogs.controller.wechat;


import com.huijava.superiorjavablogs.common.enums.ResultEnum;
import com.huijava.superiorjavablogs.common.exception.SellException;
import com.huijava.superiorjavablogs.configurer.WechatConfig;
import com.huijava.superiorjavablogs.configurer.WxMpConfiguration;
import com.huijava.superiorjavablogs.dto.WxUsersDTO;
import com.huijava.superiorjavablogs.entity.RedPacket;
import com.huijava.superiorjavablogs.entity.WxUsers;
import com.huijava.superiorjavablogs.service.RedPacketService;
import com.huijava.superiorjavablogs.service.WxUsersService;
import com.huijava.superiorjavablogs.util.ConfusionIdUtils;
import com.huijava.superiorjavablogs.util.SessionUtils;
import com.huijava.superiorjavablogs.util.WechatUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping({"wechat"})
public class WechatController {


    @Autowired
    private WechatConfig wechatConfig;

    @Autowired
    private WxUsersService wxUsersService;

    @Autowired
    private RedPacketService redPacketService;

    /**
     * 访问微信主页
     *
     * @param model
     * @return
     */
    @RequestMapping({"test"})
    public ModelAndView index(Model model) {
        model.addAttribute("appid", wechatConfig.getAppId());
        return new ModelAndView("wechat/not-followed");
    }

    /**
     * 微信公众号
     * 微信授权获取openid
     *
     * @param returnUrl
     * @return
     */
    @RequestMapping("/authorize")
    public String authorize(@RequestParam(value = "returnUrl", required = false, defaultValue = "huijava") String returnUrl) {
        //1.配置
        //2.调用方法
        //重定向地址 配置到配置文件中去
        String url = wechatConfig.getWechatProjectUrl() + "/wechat/userInfoOpenId";
        log.info("进入微信授权的首页面，returnUrl={},url={}", returnUrl, url);
        try {
            WxMpService wxMpService = WxMpConfiguration.getMpServices().get(wechatConfig.getAppId());
            if (StringUtils.isBlank(returnUrl) || returnUrl.length() >= 128) {
                returnUrl = "huijava";
            }
            //第三个参数为附加参数，我们传过去啥，就会带回来啥。最多128字节
            String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE, URLEncoder.encode(returnUrl, "utf-8"));
            log.info("[微信网页授权] 进入微信授权的首页面,获取Code，redirectUrl={}", redirectUrl);
            //重定向到请求授权页面
            return "redirect:" + redirectUrl;
        } catch (UnsupportedEncodingException e) {
            log.error("[微信网页授权] 进入微信授权的首页面,Urlencode出现异常，异常信息={}", e.getMessage());
            throw new SellException(ResultEnum.WECHAT_MP_ERROR);
        }
    }

    /**
     * 微信公众号 重定向-获取openid - 静默授权
     *
     * @param code
     * @param returnUrl
     * @return
     */
    @RequestMapping("/userInfoOpenId")
    public String userInfoOpenId(@RequestParam("code") String code,
                                 @RequestParam("state") String returnUrl) {
        log.info("进入微信授权后的重定向页面，code={},returnUrl={}", code, returnUrl);
        //state是我们自己传的
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            WxMpService wxMpService = WxMpConfiguration.getMpServices().get(wechatConfig.getAppId());
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            log.info("进入微信授权后的重定向页面,wxMpOAuth2AccessToken={}", wxMpOAuth2AccessToken);
        } catch (WxErrorException e) {
            log.error("[微信网页授权] 进入微信授权后的重定向页面,获取用户accessToken出现异常，异常信息：{}", e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        String url = wechatConfig.getWechatProjectUrl() + "/wechat/userInfo";
        log.info("[微信网页授权] 进入微信授权后的重定向页面,openid={},returnUrl={},url={}", openId, returnUrl, url);
        //重定向
        return "redirect:" + url + "?openid=" + openId;
    }


    /**
     * 微信公众号 - 跳转红包页面
     *
     * @param openid
     * @return
     */
    @RequestMapping("/userInfo")
    public ModelAndView userInfo(@RequestParam("openid") String openid,
                                 HttpServletRequest request, Model model) {
        log.info("微信授权后跳转红包页面，openid={}", openid);
        if (StringUtils.isBlank(openid)) {
            log.warn("微信授权后跳转红包页面,openid为空,参数不正确");
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
        }
        //通过openid查询数据库
        WxUsers wxUsers = wxUsersService.getWxUsersByOpenId(openid);
        log.info("微信授权后跳转红包页面，wxUsers={}", wxUsers);
        if (wxUsers == null) {
            wxUsers = getWxUsers(openid);
        }

        if (wxUsers.getSubscribe() == 0) {
            //没有关注公众号 - 返回让用户关注公众号的提示页面
            log.info("微信授权后跳转红包页面，用户没有关注公众号，用户信息:{}", wxUsers);
            return new ModelAndView("wechat/not-followed");
        }
        SessionUtils.setAttribute(request, "wxUsers", wxUsers);
        //3.设置用户信息
        WxUsersDTO wxUsersDTO = new WxUsersDTO();
        BeanUtils.copyProperties(wxUsers, wxUsersDTO);
        log.info("微信授权后跳转红包页面,用户信息:wxUsersDTO={},wxUsers={}", wxUsersDTO, wxUsers);
        model.addAttribute("wxUsersDTO", wxUsersDTO);
        //4.展示该用户剩余可获取红包口令次数
        RedPacket redPacket = redPacketService.getByWxUsersId(wxUsers.getId());
        log.info("微信授权后跳转红包页面,用户红包信息:redPacket={}", redPacket);
        model.addAttribute("redPacket", redPacket);
        //获取已被领取的红包

        return new ModelAndView("wechat/index");
    }

    private WxUsers getWxUsers(String openid) {
        //1.查询出来的数据为null
        //通过获取基本的access_token-2000次
        String accessToken = WechatUtils.getAccessToken(wechatConfig.getAppId(), wechatConfig.getAppSecret(), 2);
        //2.需要用户授权，获取用户基本信息
        WxUsers nowWxUsers = WechatUtils.getWxUsers(accessToken, openid, 2);
        log.info("微信授权后跳转红包页面,获取的用户信息为:{}", nowWxUsers);
        if (nowWxUsers == null) {
            log.error("微信授权后跳转红包页面，获取的用户信息为null：openid={},accessToken={}", openid, accessToken);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), ResultEnum.WECHAT_MP_ERROR.getMessage());
        }
        //插入数据
        int row = wxUsersService.insertSelective(nowWxUsers);
        log.info("微信授权后跳转红包页面,影响行数为={},插入数据后用户信息为:{}", row, nowWxUsers);
        if (row != 1) {
            log.error("微信授权后跳转红包页面，插入数据失败：row={},nowWxUsers={}", row, nowWxUsers);
            throw new SellException(ResultEnum.INSERT_DATA_FAILED.getCode(), ResultEnum.INSERT_DATA_FAILED.getMessage());
        }
        //使用id进行生成邀请码 - 缺点-需要再修改一次数据库数据
        Integer id = nowWxUsers.getId();
        String invitationCode = ConfusionIdUtils.encode(id.longValue());
        RedPacket redPacket = new RedPacket();
        redPacket.setWxUsersId(id);
        redPacket.setInvitationCode(invitationCode);
        redPacket.setMaxTimes(1);

        row = redPacketService.insertSelective(redPacket);
        log.info("微信授权后跳转红包页面,影响行数为={},插入红包数据后信息为:{}", row, redPacket);
        if (row != 1) {
            //有可能是邀请码重复了，进行重试一次
            RedPacket packet = redPacketService.getByInvitationCode(invitationCode);
            log.warn("微信授权后跳转红包页面,影响行数不为1，row={},通过邀请码查询的数据为:{},邀请码={}", row, redPacket, invitationCode);
            if (packet == null) {
                //重试一次
                row = redPacketService.insertSelective(redPacket);
                log.warn("微信授权后跳转红包页面,第一次插入红包数据失败，进行重试，row={},redPacket={}", row, redPacket);
                if (row != 1) {
                    log.error("微信授权后跳转红包页面，插入红包的数据失败：row={},nowWxUsers={},redPacket", row, nowWxUsers, redPacket);
                    throw new SellException(ResultEnum.INSERT_DATA_FAILED.getCode(), ResultEnum.INSERT_DATA_FAILED.getMessage());
                }
            } else {
                //说明红包数据邀请码重复了，这种情况下直接使用uuid进行作为邀请码
                redPacket.setInvitationCode(UUID.randomUUID().toString().replace("-", ""));
                row = redPacketService.insertSelective(redPacket);
                log.warn("微信授权后跳转红包页面,第一次插入红包数据由于邀请码重复失败，进行重试，row={},redPacket={}", row, redPacket);
                if (row != 1) {
                    log.error("微信授权后跳转红包页面，邀请码重复后，再次插入红包的数据失败：row={},nowWxUsers={},redPacket", row, nowWxUsers, redPacket);
                    throw new SellException(ResultEnum.INSERT_DATA_FAILED.getCode(), ResultEnum.INSERT_DATA_FAILED.getMessage());
                }
            }
        }
        //插入用户信息到表中
        return nowWxUsers;
    }


}
