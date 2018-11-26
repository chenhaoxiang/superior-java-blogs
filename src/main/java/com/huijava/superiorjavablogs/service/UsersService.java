package com.huijava.superiorjavablogs.service;

import com.huijava.superiorjavablogs.common.base.Service;
import com.huijava.superiorjavablogs.entity.Users;


/**
 * @author chenhaoxiang
 * @date 2018-09-12 18:23:40
 */
public interface UsersService extends Service<Users> {
    /**
     * 通过用户名获取用户
     *
     * @param userName
     * @return
     */
    Users selectUsersByUserName(String userName);

    /**
     * 管理员注册
     *
     * @param users
     * @return
     */
    Integer registerAdmin(Users users);

    /**
     * 管理员修改密码
     *
     * @param users
     * @return
     */
    Integer updatePassword(Users users);
}
