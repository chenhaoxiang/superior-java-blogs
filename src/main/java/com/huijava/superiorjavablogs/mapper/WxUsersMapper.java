package com.huijava.superiorjavablogs.mapper;

import com.huijava.superiorjavablogs.common.base.Mapper;
import com.huijava.superiorjavablogs.entity.WxUsers;
import org.apache.ibatis.annotations.CacheNamespaceRef;

@CacheNamespaceRef(WxUsersMapper.class)
public interface WxUsersMapper extends Mapper<WxUsers> {
}