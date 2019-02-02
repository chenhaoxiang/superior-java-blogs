package com.huijava.superiorjavablogs.mapper;

import com.huijava.superiorjavablogs.dto.RedPacketDetailsDTO;

import java.util.List;

public interface RedPacketDetailsMapperExt {
    /**
     * 获取当前用户领取的红包
     *
     * @param wxUsersId
     * @return
     */
    List<RedPacketDetailsDTO> findRedPacketDetailsDTOList(Integer wxUsersId);
}