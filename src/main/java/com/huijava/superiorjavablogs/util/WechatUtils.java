package com.huijava.superiorjavablogs.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.huijava.superiorjavablogs.scheduler.WechatScheduler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

/**
 * 微信公众号配置工具类
 */
@Slf4j
public class WechatUtils {

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
        if (accessJons.contains("access_token")) {
            HashMap hashMap = JSONObject.parseObject(accessJons, HashMap.class);
            access = (String) hashMap.get("access_token");
            Integer time = (Integer) hashMap.get("expires_in");
            WechatScheduler.accessMap.put("access_token", access);
            WechatScheduler.accessMap.put("expires_in", time);
        } else {
            //出现了异常
            log.error("获取accessToken出现了异常，url={},异常信息:{}", url, accessJons);
            //重试一次
            times--;
            return getAccessToken(appid, secret, times);
        }
        return access;
    }

}
