package com.huijava.superiorjavablogs.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RedPacketDTO {
    /**
     * 已领金额
     */
    private BigDecimal sumMoney;

    /**
     * 领取时间
     */
    private Date getTime;

    /**
     * 昵称
     */
    private String nickname;
}