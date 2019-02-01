package com.huijava.superiorjavablogs.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RedPacketDTO {
    /**
     * 已领金额
     */
    private BigDecimal sumMoney;

    /**
     * 领取时间
     */
    private Long getTime;

    /**
     * 昵称
     */
    private String nickname;
}