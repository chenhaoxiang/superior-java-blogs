package com.huijava.superiorjavablogs.controller.wechat;


import com.huijava.superiorjavablogs.common.enums.ResultEnum;
import com.huijava.superiorjavablogs.common.exception.SellException;
import com.huijava.superiorjavablogs.configurer.WechatConfig;
import com.huijava.superiorjavablogs.configurer.WxMpConfiguration;
import com.huijava.superiorjavablogs.entity.WxUsers;
import com.huijava.superiorjavablogs.service.WxUsersService;
import com.huijava.superiorjavablogs.util.WechatUtils;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@Slf4j
@RequestMapping({"wechat"})
public class WechatController {


    @Autowired
    private WechatConfig wechatConfig;

    @Autowired
    private WxUsersService wxUsersService;

    /**
     * 访问微信主页
     *
     * @param model
     * @return
     */
    @RequestMapping({"index"})
    public ModelAndView index(Model model) {
        log.debug("访问微信主页");
        model.addAttribute("appid", wechatConfig.getAppId());
        return new ModelAndView("wechat/index");
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
    public ModelAndView userInfo(@RequestParam("openid") String openid, Model model) {
        log.info("微信授权后跳转红包页面，openid={}", openid);
        //通过openid查询数据库
        WxUsers wxUsers = wxUsersService.getWxUsersByOpenId(openid);
        log.info("微信授权后跳转红包页面，wxUsers={}", wxUsers);
        if (wxUsers == null) {
            //1.查询出来的数据为null
            //通过获取基本的access_token-2000次
            String accessToken = WechatUtils.getAccessToken(wechatConfig.getAppId(), wechatConfig.getAppSecret(), 2);
            //2.需要用户授权，获取用户基本信息
            WxUsers nowWxUsers = WechatUtils.getWxUsers(accessToken, openid, 2);
            if (nowWxUsers == null) {
                log.error("微信授权后跳转红包页面，获取的用户信息为null：openid={},accessToken={}", openid, accessToken);
                throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), ResultEnum.WECHAT_MP_ERROR.getMessage());
            }
            //插入数据
            int row = wxUsersService.insert(nowWxUsers);
            if (row != 1) {
                log.error("微信授权后跳转红包页面，插入数据失败：row={},nowWxUsers={}", row, nowWxUsers);
                throw new SellException(ResultEnum.INSERT_DATA_FAILED.getCode(), ResultEnum.INSERT_DATA_FAILED.getMessage());
            }
            //使用id进行生成邀请码 - 缺点-需要再修改一次数据库数据



            //插入用户信息到表中


        }


        //3.展示该用户获取的口令

        //4.展示该用户剩余可获取红包口令次数

        return new ModelAndView("");
    }


}
