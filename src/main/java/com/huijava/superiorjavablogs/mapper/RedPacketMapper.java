package com.huijava.superiorjavablogs.mapper;

import com.huijava.superiorjavablogs.common.base.Mapper;
import com.huijava.superiorjavablogs.entity.RedPacket;
import org.apache.ibatis.annotations.CacheNamespaceRef;

@CacheNamespaceRef(RedPacketMapper.class)
public interface RedPacketMapper extends Mapper<RedPacket> {
}