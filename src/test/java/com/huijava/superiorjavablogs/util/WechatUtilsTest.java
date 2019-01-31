package com.huijava.superiorjavablogs.util;

import com.huijava.superiorjavablogs.BaseTest;
import com.huijava.superiorjavablogs.configurer.WechatConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class WechatUtilsTest extends BaseTest {

    @Autowired
    private WechatConfig wechatConfig;

    @Test
    public void getAccessToken() {
        WechatUtils.getAccessToken(wechatConfig.getAppId(), wechatConfig.getAppSecret(), 2);
    }
}