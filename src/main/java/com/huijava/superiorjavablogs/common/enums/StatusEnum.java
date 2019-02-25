/**
 * huijava.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.common.enums;

/**
 * 状态枚举
 * 0-正常 1-逻辑删除
 *
 * @author chenhx
 * @version StatusEnum.java, v 0.1 2018-07-29 下午 6:57
 */
public enum StatusEnum {
    /**
     * 正常状态
     */
    ACTIVE(0, "激活")
    /*
     * 被逻辑删除
     */, DISABLE(1, "禁用")
    /*
     * 等待审核通过
     */, WAITREVIEW(2, "待审核")
    /**
     * 待激活
     */
    , TO_BE_ACTIVATED(3, "待激活");
    private int code;
    private String message;

    StatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public int getCode() {
        return code;
    }

    /**
     * Getter method for property <tt>message</tt>.
     *
     * @return property value of message
     */
    public String getMessage() {
        return message;
    }
}