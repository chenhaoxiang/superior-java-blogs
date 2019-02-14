package com.huijava.superiorjavablogs.mapper;

import com.huijava.superiorjavablogs.common.base.Mapper;
import com.huijava.superiorjavablogs.entity.Users;
import org.apache.ibatis.annotations.CacheNamespaceRef;

@CacheNamespaceRef(UsersMapper.class)
public interface UsersMapper extends Mapper<Users> {
}