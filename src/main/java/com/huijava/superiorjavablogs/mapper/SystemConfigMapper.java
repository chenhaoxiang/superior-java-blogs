package com.huijava.superiorjavablogs.mapper;

import com.huijava.superiorjavablogs.common.base.Mapper;
import com.huijava.superiorjavablogs.entity.SystemConfig;
import org.apache.ibatis.annotations.CacheNamespaceRef;

@CacheNamespaceRef(SystemConfigMapper.class)
public interface SystemConfigMapper extends Mapper<SystemConfig> {
}