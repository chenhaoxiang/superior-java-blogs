package com.huijava.superiorjavablogs.service.impl;

import com.huijava.superiorjavablogs.common.base.AbstractService;
import com.huijava.superiorjavablogs.dto.RedPacketDTO;
import com.huijava.superiorjavablogs.entity.RedPacket;
import com.huijava.superiorjavablogs.mapper.RedPacketMapper;
import com.huijava.superiorjavablogs.mapper.RedPacketMapperExt;
import com.huijava.superiorjavablogs.service.RedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author chenhaoxiang
 * @date 2019-02-01 01:41:24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RedPacketServiceImpl extends AbstractService<RedPacket> implements RedPacketService {
    @Autowired
    private RedPacketMapper redPacketMapper;
    @Autowired
    private RedPacketMapperExt redPacketMapperExt;

    @Override
    public int insertSelective(RedPacket redPacket) {
        return redPacketMapper.insertSelective(redPacket);
    }

    @Override
    public RedPacket getByInvitationCode(String invitationCode) {
        Example example = new Example(RedPacket.class);
        example.createCriteria().andCondition("invitation_code = ", invitationCode);
        return redPacketMapper.selectOneByExample(example);
    }

    @Override
    public RedPacket getByWxUsersId(Integer wxUsersId) {
        Example example = new Example(RedPacket.class);
        example.createCriteria().andCondition("wx_users_id = ", wxUsersId);
        return redPacketMapper.selectOneByExample(example);
    }

    @Override
    public List<RedPacketDTO> findRedPacketDTOList() {
        return redPacketMapperExt.findRedPacketDTOList();
    }
}
