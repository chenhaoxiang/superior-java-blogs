/**
 * huijava.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.common.constant;

/**
 * 常量
 *
 * @author chenhx
 * @version RedisConstant.java, v 0.1 2018-07-29 下午 6:27
 */
public class UserConstans {
    /**
     * 预防一个ip下大量请求登录，在redis中存储的管理员数据key前缀
     * 存储30s内请求次数
     */
    public static String ADMIN_LOGIN_PRECAUTION_IP_KEY = "_admin_login_precaution_ip_key_";
    /**
     * 同一IP下，5秒内最多请求次数
     */
    public static Integer LOGIN_REQUEST_5S_TIMES = 10;
    /**
     * 管理员登录时候，在redis中存储的管理员数据key前缀,存储用户
     */
    public static String ADMIN_AND_USER_LOGIN_KEY = "_admin_and_user_login_key_";
    /**
     * 管理员登录 存放在客户端的token
     */
    public static String ADMIN_LOGIN_TOKEN_COOKIE = "_login_token_";
    /**
     * 没有任何操作的时候，在Redis中登录的过期时间,30分钟
     */
    public static Long ADMIN_LOGIN_EXPIRATION_TIME = 60 * 60 * 30L;
    /**
     * 用户登录，存放在session中的name  UsersDTO
     */
    public static String LOGIN_SESSION_NAME = "_login_session_user_";
    /**
     * 用户登录，存放在cookie中的name  UsersDTO
     */
    public static String LOGIN_COOKIE_NAME = "_login_cookie_user_";
}