package com.huijava.superiorjavablogs.service.impl;

import com.huijava.superiorjavablogs.common.base.AbstractService;
import com.huijava.superiorjavablogs.entity.RedPacketDetails;
import com.huijava.superiorjavablogs.mapper.RedPacketDetailsMapper;
import com.huijava.superiorjavablogs.service.RedPacketDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenhaoxiang
 * @date 2019-02-01 01:41:24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RedPacketDetailsServiceImpl extends AbstractService<RedPacketDetails> implements RedPacketDetailsService {
    @Autowired
    private RedPacketDetailsMapper redPacketDetailsMapper;

}
