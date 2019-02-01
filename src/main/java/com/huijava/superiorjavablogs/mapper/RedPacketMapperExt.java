package com.huijava.superiorjavablogs.mapper;

import com.huijava.superiorjavablogs.dto.RedPacketDTO;

import java.util.List;

public interface RedPacketMapperExt {
    /**
     * 获取已领取的金额和人数
     *
     * @return
     */
    List<RedPacketDTO> findRedPacketDTOList();
}