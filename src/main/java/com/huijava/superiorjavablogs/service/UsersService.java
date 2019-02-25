package com.huijava.superiorjavablogs.service;

import com.huijava.superiorjavablogs.common.base.Service;
import com.huijava.superiorjavablogs.entity.Users;


/**
 * @author chenhaoxiang
 * @date 2018-09-12 18:23:40
 */
public interface UsersService extends Service<Users> {

    /**
     * 通过id修改用户信息
     *
     * @param users
     * @return
     */
    Integer updateByPrimaryKeySelective(Users users);

    Integer insertSelective(Users users);
    /**
     * 通过用户名获取用户
     *
     * @param userName
     * @return
     */
    Users selectUsersByUserName(String userName);

    /**
     * 通过用户名获取用户数
     *
     * @param userName
     * @return
     */
    Integer selectUsersCountByUserName(String userName);


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

    /**
     * 通过邮件获取用户
     *
     * @param email
     * @return
     */
    Users selectUsersByEmail(String email);

    /**
     * 通过邮件获取用户数
     *
     * @param email
     * @return
     */
    Integer selectUsersCountByEmail(String email);


}
