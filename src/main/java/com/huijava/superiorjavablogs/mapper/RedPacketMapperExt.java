package com.huijava.superiorjavablogs.mapper;

import com.huijava.superiorjavablogs.dto.RedPacketDTO;

import java.math.BigDecimal;
import java.util.List;

public interface RedPacketMapperExt {
    /**
     * 获取已领取的金额和人数
     *
     * @return
     */
    List<RedPacketDTO> findRedPacketDTOList();

    /**
     * 修改用户获取的红包金额以及增加次数
     *
     * @param money
     * @return
     */
    Integer updateTimesAndSumMoneyByWxUsersId(Integer wxUsersId, BigDecimal money);
}