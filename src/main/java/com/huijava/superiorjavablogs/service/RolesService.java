package com.huijava.superiorjavablogs.service;

import com.huijava.superiorjavablogs.common.base.Service;
import com.huijava.superiorjavablogs.entity.Roles;


/**
 * @author chenhaoxiang
 * @date 2018-09-12 18:23:40
 */
public interface RolesService extends Service<Roles> {

    Roles selectByName(String users);

    Integer insertSelective(Roles roles);
}
