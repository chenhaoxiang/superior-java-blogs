/**
 * huijava.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.common.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 所有实体类的超类
 *
 * @author chenhx
 * @version BaseEntity.java, v 0.1 2018-09-12 下午 8:55
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -4282387477058447315L;
    private Integer id;
    private Date createTime;
    private Date updateTime;
    private Integer createId;
    private Integer updateId;
}