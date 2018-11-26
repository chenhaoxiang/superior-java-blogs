/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.common.enums;

/**
 * 博客原创标识
 *
 * @author chenhx
 * @version BlogOriginalEnum.java, v 0.1 2018-09-19 下午 8:13
 */
public enum BlogOriginalEnum {
    /**
     * 原创标志 0-转载 1-原创 2-翻译
     */
    REPRINT(0, "转载"), ORIGINAL(1, "原创"), TRANSLATION(2, "翻译");

    private Integer code;
    private String name;

    BlogOriginalEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * Getter method for property <tt>code</tt>.
     *
     * @return property value of code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }
}
