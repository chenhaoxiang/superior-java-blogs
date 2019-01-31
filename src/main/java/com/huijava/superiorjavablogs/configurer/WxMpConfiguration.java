package com.huijava.superiorjavablogs.configurer;

import com.google.common.collect.Maps;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;

@Configuration
public class WxMpConfiguration {
    private static Map<String, WxMpService> mpServices = Maps.newHashMap();
    @Autowired
    private WechatConfig wechatConfig;

    public static Map<String, WxMpService> getMpServices() {
        return mpServices;
    }

    @PostConstruct
    public void initServices() {
        WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
        configStorage.setAppId(wechatConfig.getAppId());
        configStorage.setSecret(wechatConfig.getAppSecret());
//        configStorage.setToken(a.getToken());
//        configStorage.setAesKey(a.getAesKey());
        WxMpService service = new WxMpServiceImpl();
        service.setWxMpConfigStorage(configStorage);
        mpServices.put(wechatConfig.getAppId(), service);
    }


}
