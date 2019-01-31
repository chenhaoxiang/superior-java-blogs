package com.huijava.superiorjavablogs.configurer;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class WechatConfig {

    /**
     * 公众号appId
     */
    @Value("${wechat.appid}")
    private String appId;
    /**
     * 公众号appSecret
     */
    @Value("${wechat.appsecret}")
    private String appSecret;
    /**
     * wechat.project.url.wechat.mp.authorize
     */
    @Value("${wechat.project.url.wechat.mp.authorize}")
    private String wechatProjectUrlWechatMpAuthorize;
    /**
     * wechat.project.url
     */
    @Value("${wechat.project.url}")
    private String wechatProjectUrl;

}
