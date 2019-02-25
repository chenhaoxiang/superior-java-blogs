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
import tk.mybatis.mapper.entity.Example;

/**
 * @author chenhaoxiang
 * @date 2018-09-12 18:23:40
 */
@Service
public class UsersServiceImpl extends AbstractService<Users> implements UsersService {
    @Autowired
    private UsersMapperExt usersMapperExt;
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Integer updateByPrimaryKeySelective(Users users) {
        return usersMapper.updateByPrimaryKeySelective(users);
    }

    @Override
    public Users selectUsersByUserName(String userName) {
        return usersMapperExt.selectByUserName(userName);
    }

    @Override
    public Integer insertSelective(Users users) {
        return usersMapper.insertSelective(users);
    }

    @Override
    public Integer selectUsersCountByUserName(String userName) {
        Example example = new Example(Users.class);
        example.createCriteria().andCondition("username=", userName);
        return usersMapper.selectCountByExample(example);
    }

    @Override
    public Users selectUsersByEmail(String email) {
        Example example = new Example(Users.class);
        example.createCriteria().andCondition("email=", email);
        return usersMapper.selectOneByExample(example);
    }

    @Override
    public Integer selectUsersCountByEmail(String email) {
        Example example = new Example(Users.class);
        example.createCriteria().andCondition("email=", email);
        return usersMapper.selectCountByExample(example);
    }

    /**
     * 管理员注册
     *
     * @param users
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer registerAdmin(Users users) {
        Users newUser = new Users();
        BeanUtils.copyProperties(users, newUser);
        newUser.setSalt(PasswordUtils.getSalt());
        newUser.setRolesId(RoleEnum.ADMIN.getRoleId());
        newUser.setPassword(PasswordUtils.getPassword(newUser.getPassword(), newUser.getSalt()));
        return usersMapper.insertSelective(newUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
