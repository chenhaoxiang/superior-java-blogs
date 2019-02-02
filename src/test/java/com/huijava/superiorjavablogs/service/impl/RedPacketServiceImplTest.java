package com.huijava.superiorjavablogs.service.impl;

import com.huijava.superiorjavablogs.BaseTest;
import com.huijava.superiorjavablogs.entity.RedPacket;
import com.huijava.superiorjavablogs.entity.WxUsers;
import com.huijava.superiorjavablogs.service.RedPacketService;
import com.huijava.superiorjavablogs.service.WxUsersService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class RedPacketServiceImplTest extends BaseTest {

    @Autowired
    private RedPacketService redPacketService;

    @Autowired
    private WxUsersService wxUsersService;

    @Test
    public void insertSelective() {
    }

    @Test
    public void getByInvitationCode() {
    }

    @Test
    public void getByWxUsersId() {
    }

    @Test
    public void findRedPacketDTOList() {
    }

    @Test
    public void getRedPacket() {
        RedPacket redPacket = redPacketService.selectById(2);
        WxUsers wxUsers = wxUsersService.selectById(2);
        log.info("redPacket={},wxUsers={}", redPacket, wxUsers);

        String message = redPacketService.getRedPacket(redPacket, wxUsers);
        log.info("message={}", message);
    }

}