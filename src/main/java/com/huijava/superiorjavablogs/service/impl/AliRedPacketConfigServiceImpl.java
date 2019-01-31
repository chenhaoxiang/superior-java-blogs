package com.huijava.superiorjavablogs.service.impl;

import com.huijava.superiorjavablogs.common.base.AbstractService;
import com.huijava.superiorjavablogs.entity.AliRedPacketConfig;
import com.huijava.superiorjavablogs.mapper.AliRedPacketConfigMapper;
import com.huijava.superiorjavablogs.service.AliRedPacketConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenhaoxiang
 * @date 2019-02-01 01:41:24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AliRedPacketConfigServiceImpl extends AbstractService<AliRedPacketConfig> implements AliRedPacketConfigService {
    @Autowired
    private AliRedPacketConfigMapper aliRedPacketConfigMapper;

}
