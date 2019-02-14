package com.huijava.superiorjavablogs.mapper;

import com.huijava.superiorjavablogs.common.base.Mapper;
import com.huijava.superiorjavablogs.entity.Roles;
import org.apache.ibatis.annotations.CacheNamespaceRef;

@CacheNamespaceRef(RolesMapper.class)
public interface RolesMapper extends Mapper<Roles> {
}