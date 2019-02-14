package com.huijava.superiorjavablogs.service.impl;

import com.huijava.superiorjavablogs.common.base.AbstractService;
import com.huijava.superiorjavablogs.entity.Roles;
import com.huijava.superiorjavablogs.mapper.RolesMapper;
import com.huijava.superiorjavablogs.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenhaoxiang
 * @date 2018-09-12 18:23:40
 */
@Service
public class RolesServiceImpl extends AbstractService<Roles> implements RolesService {
    @Autowired
    private RolesMapper rolesMapper;

}
