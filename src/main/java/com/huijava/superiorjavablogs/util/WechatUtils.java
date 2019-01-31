package com.huijava.superiorjavablogs.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huijava.superiorjavablogs.entity.WxUsers;
import com.huijava.superiorjavablogs.form.WxUserInfoForm;
import com.huijava.superiorjavablogs.scheduler.WechatScheduler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.HashMap;

/**
 * 微信公众号配置工具类
 */
@Slf4j
public class WechatUtils {

    /**
     * 获取微信的基本Token - 每天限制2000次
     *
     * @param appid
     * @param secret
     * @param times
     * @return
     */
    public static String getAccessToken(String appid, String secret, Integer times) {
        if (times.equals(0)) {
            return "";
        }
        String access = "";
        try {
            access = (String) WechatScheduler.accessMap.get("access_token");
        } catch (Exception e) {
            log.warn("获取access_token出现异常");
        }
        if (StringUtils.isNoneBlank(access)) {
            return access;
        }
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
        String accessJons = HttpUtil.get(url, 10000);
        log.info("获取的accessToken为:{}", accessJons);
        if (accessJons.contains("errcode")) {
            //出现了异常
            log.error("获取accessToken出现了异常，url={},异常信息:{}", url, accessJons);
            //重试一次
            times--;
            return getAccessToken(appid, secret, times);
        } else {
            HashMap hashMap = JSONObject.parseObject(accessJons, HashMap.class);
            access = (String) hashMap.get("access_token");
            Integer time = (Integer) hashMap.get("expires_in");
            WechatScheduler.accessMap.put("access_token", access);
            WechatScheduler.accessMap.put("expires_in", time);
        }
        return access;
    }

    /**
     * 获取用户基本信息
     *
     * @param accessToken
     * @param openid
     * @param times
     * @return
     */
    public static WxUsers getWxUsers(String accessToken, String openid, Integer times) {
        if (times.equals(0)) {
            return null;
        }
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken + "&openid=" + openid + "&lang=zh_CN";
        WxUsers wxUsers = new WxUsers();
        String usersJson = HttpUtil.get(url, 10000);
        log.info("获取用户的基本信息:times={},url={},usersJson={}", times, url, usersJson);
        if (usersJson.contains("errcode")){
            //出现了异常
            log.error("获取用户的基本信息出现了异常，url={},异常信息:{}", url, usersJson);
            //重试一次
            times--;
            return getWxUsers(accessToken, openid, times);
        } else {
            WxUserInfoForm wxUserInfoForm = JSON.parseObject(usersJson, WxUserInfoForm.class);
            BeanUtils.copyProperties(wxUserInfoForm, wxUsers);
            log.info("获取用户的基本信息,wxUsers={},wxUserInfoForm={}", wxUsers, wxUserInfoForm);
        }
        return wxUsers;
    }
}
