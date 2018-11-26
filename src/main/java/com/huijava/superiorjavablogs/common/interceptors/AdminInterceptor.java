/**
 * huijava.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.common.interceptors;

import com.alibaba.fastjson.JSON;
import com.huijava.superiorjavablogs.common.constant.UserConstans;
import com.huijava.superiorjavablogs.common.enums.RoleEnum;
import com.huijava.superiorjavablogs.entity.Users;
import com.huijava.superiorjavablogs.service.UsersService;
import com.huijava.superiorjavablogs.util.CookieUtils;
import com.huijava.superiorjavablogs.util.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 实现管理员登录拦截器
 *
 * @author chenhx
 * @version AdminInterceptor.java, v 0.1 2018-07-29 下午 7:06
 */
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {

    @Autowired
    private UsersService usersService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Users usersDTO = SessionUtils.getAttribute(request, UserConstans.LOGIN_SESSION_NAME);
        if (usersDTO == null) {
            //获取cookie中的User信息
            Users users = (Users) JSON.parse(CookieUtils.getCookie(request, UserConstans.LOGIN_COOKIE_NAME));
            log.debug("获取cookie中的User信息={}", users);
            if (users != null) {
                Users realUsers = usersService.selectById(users.getId());
                log.debug("realUsers={}", users);
                //TODO 最好使用令牌，并且根据时间来生成
                if (users.getPassword().equals(realUsers.getPassword())) {
                    if (RoleEnum.ADMIN.getRoleId().equals(users.getRolesId())) {
                        return true;
                    }
                }
            }
            //TODO 记录当前页面的地址，可以在登录成功后直接跳转
            response.sendRedirect("/admin/login");
            return false;
        } else {
            if (RoleEnum.ADMIN.getRoleId().equals(usersDTO.getRolesId())) {
                return true;
            }
            log.debug("不是管理员,user={}", usersDTO);
            response.sendRedirect("/admin/login");
//                request.getRequestDispatcher("/admin/login").forward(request, response);
            return false;
        }
    }
}