/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.dto;

import com.huijava.superiorjavablogs.common.base.BaseEntity;
import lombok.Data;

/**
 * @author chenhx
 * @version UsersDTO.java, v 0.1 2018-09-20 上午 2:14
 */
@Data
public class UsersDTO extends BaseEntity {

    /**
     * 用户名
     */
    private String username;


    /**
     * 电子邮箱
     */
    private String email;


    /**
     * 状态 0-正常 1-逻辑删除
     */
    private Byte status;


    /**
     * 用户的主页url
     */
    private String homeUrl;

    /**
     * 个性签名
     */
    private String sign;


    /**
     * 昵称
     */
    private String nikeName;

    /**
     * 头像地址
     */
    private String headImage;


    /**
     * 贡献点
     */
    private Long contributeCount;

    /**
     * 贡献权重 - 贡献度 - 相当于累计的贡献点
     */
    private Long contributeWeight;

    /**
     * 粉丝数
     */
    private Integer followersCount;
}