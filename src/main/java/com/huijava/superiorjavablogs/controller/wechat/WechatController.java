package com.huijava.superiorjavablogs.controller.wechat;


import com.huijava.superiorjavablogs.common.enums.ResultEnum;
import com.huijava.superiorjavablogs.common.exception.SellException;
import com.huijava.superiorjavablogs.configurer.WechatConfig;
import com.huijava.superiorjavablogs.configurer.WxMpConfiguration;
import com.huijava.superiorjavablogs.dto.RedPacketDTO;
import com.huijava.superiorjavablogs.dto.RedPacketDetailsDTO;
import com.huijava.superiorjavablogs.dto.WxUsersDTO;
import com.huijava.superiorjavablogs.entity.RedPacket;
import com.huijava.superiorjavablogs.entity.WxUsers;
import com.huijava.superiorjavablogs.service.RedPacketDetailsService;
import com.huijava.superiorjavablogs.service.RedPacketService;
import com.huijava.superiorjavablogs.service.WxUsersService;
import com.huijava.superiorjavablogs.util.ConfusionIdUtils;
import com.huijava.superiorjavablogs.util.DateUtils;
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
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.List;
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
    @Autowired
    private RedPacketDetailsService redPacketDetailsService;

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
     * 请求绑定上级
     *
     * @param model
     * @return
     */
    @RequestMapping({"submitInvitationCode"})
    public ModelAndView submitInvitationCode(Model model, HttpServletRequest request,
                                             @RequestParam("invitationCode") String invitationCode) {
        WxUsers wxUsers = SessionUtils.getAttribute(request, "wxUsers");
        log.info("请求绑定上级，wxUsers={}", wxUsers);
        if (wxUsers == null) {
            throw new SellException(ResultEnum.LOGIN_FAIL);
        }
        wxUserCheck(wxUsers);

        //只有在2月4日之前关注的用户才能邀请下级 - 且不能绑定上级
        long time = 1549209600L;
        try {
            time = DateUtils.parseDate("2019-02-04 00:00:00", "yyyy-MM-dd HH:mm:ss").getTime() / 1000;
        } catch (ParseException e) {
            log.warn("请求绑定上级,time={},wxUsers={}", time, wxUsers);
        }

        if (wxUsers.getSubscribeTime() > time && wxUsers.getPid() == 0) {
            //关注时间大于这个点的且没有填写邀请人的才能填写邀请人
            model.addAttribute("isNewUser", 1);
        }

        if (wxUsers.getPid() == 0) {
            RedPacket redPacket = redPacketService.getByInvitationCode(invitationCode);
            log.info("请求绑定上级,通过邀请码获取信息,redPacket={},wxUsers={},invitationCode={}", redPacket, wxUsers, invitationCode);
            if (redPacket == null || redPacket.getWxUsersId() == null) {
                model.addAttribute("message", "邀请码填写错误:" + invitationCode);
            } else {
                WxUsers wxUsers1 = wxUsersService.selectById(redPacket.getWxUsersId());
                log.info("请求绑定上级,获取的上级信息，redPacket={},上级用户信息={},wxUsers={},invitationCode={}", redPacket, wxUsers1, wxUsers, invitationCode);
                if (wxUsers1 == null) {
                    model.addAttribute("message", "上级信息为空");
                } else {
                    if (wxUsers1.getSubscribeTime() < time) {
                        //将上级修改为该用户id
                        WxUsers wxUsers2 = new WxUsers();
                        wxUsers2.setId(wxUsers.getId());
                        wxUsers2.setPid(wxUsers1.getId());
                        int rows = wxUsersService.bindingWxUsersPidAndAddTimes(wxUsers2, redPacket);
                        if (rows == 1) {
                            model.addAttribute("message", "绑定成功");
                        } else {
                            model.addAttribute("message", "绑定失败");
                        }
                    } else {
                        model.addAttribute("message", "对方的关注时间大于2019年2月4日，无法邀请人");
                        model.addAttribute("isNewUser", 1);
                    }

                }
            }
        }

        //获取上级
        if (wxUsers.getPid() != 0) {
            WxUsers wxUsers2 = wxUsersService.selectById(wxUsers.getPid());
            log.info("请求绑定上级，上级信息:{},wxUsers={}", wxUsers2, wxUsers);
            if (wxUsers2 != null) {
                model.addAttribute("myPName", wxUsers2.getNickname());
            }
        }

        //获取下级人数
        List<WxUsersDTO> wxUsersDTOList = wxUsersService.findWxUsersDTOByPid(wxUsers.getId());
        log.info("请求绑定上级，下级信息:{},wxUsers={}", wxUsersDTOList, wxUsers);

        model.addAttribute("wxUsersDTOList", wxUsersDTOList);
        model.addAttribute("wxUsersDTO", wxUsers);

        return new ModelAndView("wechat/invitation");
    }


    /**
     * 领红包
     *
     * @param model
     * @return
     */
    @RequestMapping({"getRedPacket"})
    public ModelAndView getRedPacket(@RequestParam(value = "getRedPacket", required = false, defaultValue = "0") String getRedPacket,
                                     Model model, HttpServletRequest request) {
        WxUsers wxUsers = SessionUtils.getAttribute(request, "wxUsers");
        log.info("获取红包信息页面，wxUsers={}", wxUsers);
        if (wxUsers == null) {
            throw new SellException(ResultEnum.LOGIN_FAIL);
        }
        wxUserCheck(wxUsers);

        if ("1".equals(getRedPacket)) {
            //获取红包开始
            //判断领取次数
            RedPacket redPacket = redPacketService.getByWxUsersId(wxUsers.getId());
            log.info("获取红包信息页面，wxUsers={},redPacket={}", wxUsers, redPacket);
            if (redPacket.getTimes() < redPacket.getMaxTimes()) {
                //尚可以领取红包
                String message = redPacketService.getRedPacket(redPacket, wxUsers);
                model.addAttribute("message", message);
            } else {
                model.addAttribute("message", "尚无可领取次数");
            }
        }
        //获取红包信息
        List<RedPacketDetailsDTO> redPacketDetailsDTOList = redPacketDetailsService.findRedPacketDetailsDTOList(wxUsers.getId());
        log.info("获取红包信息页面,redPacketDetailsDTOList={}", redPacketDetailsDTOList);
        model.addAttribute("redPacketDetailsDTOList", redPacketDetailsDTOList);
        BigDecimal sumMoney = new BigDecimal(0);
        for (RedPacketDetailsDTO redPacketDetailsDTO : redPacketDetailsDTOList) {
            sumMoney = sumMoney.add(redPacketDetailsDTO.getMoney());
        }
        model.addAttribute("sumMoney", sumMoney);
        model.addAttribute("wxUsersDTO", wxUsers);

        //展示该用户剩余可获取红包口令次数
        RedPacket redPacket = redPacketService.getByWxUsersId(wxUsers.getId());
        log.info("获取红包信息页面,用户红包信息:redPacket={}", redPacket);
        model.addAttribute("redPacket", redPacket);

        return new ModelAndView("wechat/get-red-packet");
    }

    /**
     * 邀请
     *
     * @param model
     * @return
     */
    @RequestMapping({"invitation"})
    public ModelAndView invitation(Model model, HttpServletRequest request) {
        WxUsers wxUsers = SessionUtils.getAttribute(request, "wxUsers");
        log.info("邀请页面，wxUsers={}", wxUsers);
        if (wxUsers == null) {
            throw new SellException(ResultEnum.LOGIN_FAIL);
        }
        wxUserCheck(wxUsers);

        //只有在2月4日之前关注的用户才能邀请下级 - 且不能绑定上级
        long time = 1549209600L;
        try {
            time = DateUtils.parseDate("2019-02-04 00:00:00", "yyyy-MM-dd HH:mm:ss").getTime() / 1000;
        } catch (ParseException e) {
            log.warn("邀请页面,time={},wxUsers={}", time, wxUsers);
        }
        if (wxUsers.getSubscribeTime() > time && wxUsers.getPid() == 0) {
            //关注时间大于这个点的才能邀请人
            model.addAttribute("isNewUser", 1);
        }

        //获取上级
        if (wxUsers.getPid() != 0) {
            WxUsers wxUsers1 = wxUsersService.selectById(wxUsers.getPid());
            log.info("邀请页面，上级信息:{},wxUsers={}", wxUsers1, wxUsers);
            if (wxUsers1 != null) {
                model.addAttribute("myPName", wxUsers1.getNickname());
            }
        }

        //获取下级人数
        List<WxUsersDTO> wxUsersDTOList = wxUsersService.findWxUsersDTOByPid(wxUsers.getId());
        log.info("邀请页面，下级信息:{},wxUsers={}", wxUsersDTOList, wxUsers);

        model.addAttribute("wxUsersDTOList", wxUsersDTOList);
        model.addAttribute("wxUsersDTO", wxUsers);
        return new ModelAndView("wechat/invitation");
    }

    /**
     * 我的信息
     *
     * @param model
     * @return
     */
    @RequestMapping({"myInfo"})
    public ModelAndView myInfo(Model model, HttpServletRequest request) {
        WxUsers wxUsers = SessionUtils.getAttribute(request, "wxUsers");
        log.info("我的信息页面，wxUsers={}", wxUsers);
        if (wxUsers == null) {
            throw new SellException(ResultEnum.LOGIN_FAIL);
        }
        wxUserCheck(wxUsers);

        //根据id查询邀请人
        WxUsers wxUsers1 = wxUsersService.selectById(wxUsers.getPid());
        if (wxUsers1 != null) {
            model.addAttribute("invitationName", wxUsers1.getNickname());
        } else {
            model.addAttribute("invitationName", "无邀请人");
        }
        model.addAttribute("wxUsersDTO", wxUsers);
        return new ModelAndView("wechat/my-info");
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
    public ModelAndView userInfo(@RequestParam(value = "openid", required = false, defaultValue = "") String openid,
                                 HttpServletRequest request, Model model) {
        log.info("微信授权后跳转红包页面，openid={}", openid);
        WxUsers wxUsers = SessionUtils.getAttribute(request, "wxUsers");
        log.info("微信授权后跳转红包页面，wxUsers={}", wxUsers);
//        if(wxUsers==null){
        //通过openid查询数据库
//            wxUsers = wxUsersService.getWxUsersByOpenId(openid);
//            log.info("微信授权后跳转红包页面，通过数据库查询的数据，wxUsers={}", wxUsers);
//        }
        if (wxUsers == null) {
            if (StringUtils.isBlank(openid)) {
                log.warn("微信授权后跳转红包页面,openid为空,参数不正确");
                throw new SellException(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
            }
            //确保session过期后，重新进入会获取到最新的信息，而不是数据库的数据
            wxUsers = getWxUsers(openid);
            log.info("微信授权后跳转红包页面，第一次进入首页，wxUsers={}", wxUsers);
        }

        if (wxUsers.getSubscribe() == null || wxUsers.getSubscribe() == 0) {
            //没有关注公众号 - 返回让用户关注公众号的提示页面
            log.info("微信授权后跳转红包页面，用户没有关注公众号，用户信息:{}", wxUsers);
            return new ModelAndView("wechat/not-followed");
        }
        wxUserCheck(wxUsers);

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
        //TODO 此处可以使用缓存
        List<RedPacketDTO> redPacketDTOList = redPacketService.findRedPacketDTOList();
        for (RedPacketDTO redPacketDTO : redPacketDTOList) {
            int len = redPacketDTO.getOpenid().length();
            redPacketDTO.setOpenid("***" + redPacketDTO.getOpenid().substring((len - 6) >= 0 ? (len - 6) : 0, len));
        }
        log.info("微信授权后跳转红包页面");
        model.addAttribute("redPacketDTOList", redPacketDTOList);
        return new ModelAndView("wechat/index");
    }

    private void wxUserCheck(WxUsers wxUsers) {
        if (wxUsers.getStatus().equals(1)) {
            log.warn("微信用户校验,用户已被禁用，wxUsers={}", wxUsers);
            throw new SellException(ResultEnum.LOGIN_FAIL);
        }
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

        WxUsers wxUsers = wxUsersService.getWxUsersByOpenId(openid);
        if (wxUsers == null) {
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
        } else {
            //更新数据
            nowWxUsers.setId(wxUsers.getId());
            int row = wxUsersService.updateByPrimaryKeySelective(nowWxUsers);
            log.info("微信授权后跳转红包页面,影响行数为={},更改数据后用户信息为:{}", row, nowWxUsers);
            if (row != 1) {
                //不进行处理
                log.error("微信授权后跳转红包页面，更改数据失败：row={},nowWxUsers={}", row, nowWxUsers);
            }
        }
        //查询一次最新数据
        nowWxUsers = wxUsersService.selectById(nowWxUsers.getId());
        return nowWxUsers;
    }


}
