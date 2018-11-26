/**
 * huijava.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.common.enums;

import lombok.Getter;

/**
 * 管理员枚举类
 *
 * @author chenhx
 * @version RoleEnum.java, v 0.1 2018-07-31 上午 12:37
 */
@Getter
public enum RoleEnum {
    /**
     * 管理员
     */
    ADMIN(1, "管理员")
    /*
     * 普通用户
     */, USER(2, "普通用户");

    private Integer roleId;
    private String message;

    RoleEnum(Integer roleId, String message) {
        this.roleId = roleId;
        this.message = message;
    }


}