/**
 * huijava.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.common.enums;

import org.apache.commons.lang3.RandomUtils;

/**
 * 颜色，对应的是bootstrap中class的值
 * 例如：label-primary，但是数据库只存primary
 *
 * @author chenhx
 * @version ColorNameEnum.java, v 0.1 2018-07-31 上午 12:26
 */
public enum ColorNameEnum {
    /**
     * 灰色
     */
    DEFAULT("default")
    /*
     * 深蓝色
     */, PRIMARY("primary")
    /*
     * 绿色
     */, SUCCESS("success")
    /*
     * 浅蓝色
     */, INFO("info")
    /*
     * 淡黄色
     */, WARNING("warning")
    /*
     * 红色
     */, DANGER("danger");

    private String colorName;

    ColorNameEnum(String colorName) {
        this.colorName = colorName;
    }

    public static String getRandomColorName() {
        ColorNameEnum[] colorNameEnum = ColorNameEnum.values();
        return colorNameEnum[RandomUtils.nextInt(0, colorNameEnum.length)].getColorName();
    }

    /**
     * Getter method for property <tt>colorName</tt>.
     *
     * @return property value of colorName
     */
    public String getColorName() {
        return colorName;
    }

}
