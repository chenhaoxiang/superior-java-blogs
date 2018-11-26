package com.huijava.superiorjavablogs.service.impl;

import com.huijava.superiorjavablogs.common.base.AbstractService;
import com.huijava.superiorjavablogs.common.enums.RoleEnum;
import com.huijava.superiorjavablogs.entity.Users;
import com.huijava.superiorjavablogs.mapper.UsersMapper;
import com.huijava.superiorjavablogs.mapper.UsersMapperExt;
import com.huijava.superiorjavablogs.service.UsersService;
import com.huijava.superiorjavablogs.util.pass.PasswordUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenhaoxiang
 * @date 2018-09-12 18:23:40
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UsersServiceImpl extends AbstractService<Users> implements UsersService {
    @Autowired
    private UsersMapperExt usersMapperExt;
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Users selectUsersByUserName(String userName) {
        return usersMapperExt.selectByUserName(userName);
    }


    /**
     * 管理员注册
     *
     * @param users
     * @return
     */
    @Override
    public Integer registerAdmin(Users users) {
        Users newUser = new Users();
        BeanUtils.copyProperties(users, newUser);
        newUser.setSalt(PasswordUtils.getSalt());
        newUser.setRolesId(RoleEnum.ADMIN.getRoleId());
        newUser.setPassword(PasswordUtils.getPassword(newUser.getPassword(), newUser.getSalt()));
        return usersMapper.insertSelective(newUser);
    }

    @Override
    public Integer updatePassword(Users users) {
        //先查询出原来的用户
        Users realUser = usersMapperExt.selectByUserName(users.getUsername());
        users.setId(realUser.getId());
        users.setPassword(PasswordUtils.getPassword(users.getPassword(), realUser.getSalt()));
        Users saveUser = new Users();
        BeanUtils.copyProperties(users, saveUser);
        return usersMapper.updateByPrimaryKeySelective(saveUser);
    }

}
