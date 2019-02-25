package com.huijava.superiorjavablogs.service.impl;

import com.huijava.superiorjavablogs.common.base.AbstractService;
import com.huijava.superiorjavablogs.entity.Roles;
import com.huijava.superiorjavablogs.mapper.RolesMapper;
import com.huijava.superiorjavablogs.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author chenhaoxiang
 * @date 2018-09-12 18:23:40
 */
@Service
public class RolesServiceImpl extends AbstractService<Roles> implements RolesService {
    @Autowired
    private RolesMapper rolesMapper;

    @Override
    public Roles selectByName(String users) {
        Example example = new Example(Roles.class);
        example.createCriteria().andCondition("name=", users);
        return rolesMapper.selectOneByExample(example);
    }

    @Override
    public Integer insertSelective(Roles roles) {
        return rolesMapper.insertSelective(roles);
    }
}
