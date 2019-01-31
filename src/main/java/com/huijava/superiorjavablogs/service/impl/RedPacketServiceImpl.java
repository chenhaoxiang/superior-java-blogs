package com.huijava.superiorjavablogs.service.impl;

import com.huijava.superiorjavablogs.common.base.AbstractService;
import com.huijava.superiorjavablogs.entity.RedPacket;
import com.huijava.superiorjavablogs.mapper.RedPacketMapper;
import com.huijava.superiorjavablogs.service.RedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenhaoxiang
 * @date 2019-02-01 01:41:24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RedPacketServiceImpl extends AbstractService<RedPacket> implements RedPacketService {
    @Autowired
    private RedPacketMapper redPacketMapper;

}
