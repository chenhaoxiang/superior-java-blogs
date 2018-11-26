/**
 * huijava.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.mapper;

import com.huijava.superiorjavablogs.entity.Users;

/**
 * @author chenhx
 * @version UsersMapperExt.java, v 0.1 2018-09-12 下午 8:09
 */
public interface UsersMapperExt {
    Users selectByUserName(String userName);
}