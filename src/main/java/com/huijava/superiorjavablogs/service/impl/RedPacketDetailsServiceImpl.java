package com.huijava.superiorjavablogs.service.impl;

import com.huijava.superiorjavablogs.common.base.AbstractService;
import com.huijava.superiorjavablogs.dto.RedPacketDetailsDTO;
import com.huijava.superiorjavablogs.entity.RedPacketDetails;
import com.huijava.superiorjavablogs.mapper.RedPacketDetailsMapper;
import com.huijava.superiorjavablogs.mapper.RedPacketDetailsMapperExt;
import com.huijava.superiorjavablogs.service.RedPacketDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenhaoxiang
 * @date 2019-02-01 01:41:24
 */
@Service
public class RedPacketDetailsServiceImpl extends AbstractService<RedPacketDetails> implements RedPacketDetailsService {
    @Autowired
    private RedPacketDetailsMapper redPacketDetailsMapper;
    @Autowired
    private RedPacketDetailsMapperExt redPacketDetailsMapperExt;


    @Override
    public List<RedPacketDetailsDTO> findRedPacketDetailsDTOList(Integer wxUsersId) {
        return redPacketDetailsMapperExt.findRedPacketDetailsDTOList(wxUsersId);
    }
}
