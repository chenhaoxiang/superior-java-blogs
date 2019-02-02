package com.huijava.superiorjavablogs.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RedPacketDetailsDTO {
    /**
     * 红包口令- 20个字符以内
     */
    private String password;
    /**
     * 金额
     */
    private BigDecimal money;
    /**
     * 领取时间，时间戳
     */
    private Long getTime;
    /**
     * 创建时间
     */
    private Date createTime;
}