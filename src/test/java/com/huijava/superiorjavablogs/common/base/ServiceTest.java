package com.huijava.superiorjavablogs.common.base;

import com.huijava.superiorjavablogs.BaseTest;
import com.huijava.superiorjavablogs.entity.WxUsers;
import com.huijava.superiorjavablogs.service.WxUsersService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ServiceTest extends BaseTest {

    @Autowired
    private WxUsersService wxUsersService;


    @Test
    public void insertWxUsers() {
        WxUsers wxUsers = new WxUsers();
        wxUsers.setSubscribe((byte) 1);
        wxUsers.setOpenid("ceshi");

        log.info("wxUsers1={}", wxUsers);
        wxUsersService.insertSelective(wxUsers);
        log.info("wxUsers2={}", wxUsers);
    }
}