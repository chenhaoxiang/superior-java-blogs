package com.huijava.superiorjavablogs.mapper;

import com.huijava.superiorjavablogs.common.base.Mapper;
import com.huijava.superiorjavablogs.entity.AliRedPacketConfig;
import org.apache.ibatis.annotations.CacheNamespaceRef;

@CacheNamespaceRef(AliRedPacketConfigMapper.class)
public interface AliRedPacketConfigMapper extends Mapper<AliRedPacketConfig> {
}