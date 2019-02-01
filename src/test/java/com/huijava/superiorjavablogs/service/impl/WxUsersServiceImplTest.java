package com.huijava.superiorjavablogs.service.impl;

import com.huijava.superiorjavablogs.BaseTest;
import com.huijava.superiorjavablogs.service.WxUsersService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class WxUsersServiceImplTest extends BaseTest {

    @Autowired
    private WxUsersService wxUsersService;

    @Test
    public void getWxUsersByOpenId() {
        log.info("{}", wxUsersService.getWxUsersByOpenId("123"));
        log.info("{}", wxUsersService.getWxUsersByOpenId("2"));

    }
}