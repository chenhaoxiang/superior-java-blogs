package com.huijava.superiorjavablogs.mapper;

import com.huijava.superiorjavablogs.common.base.Mapper;
import com.huijava.superiorjavablogs.entity.RedPacketDetails;
import org.apache.ibatis.annotations.CacheNamespaceRef;

@CacheNamespaceRef(RedPacketDetailsMapper.class)
public interface RedPacketDetailsMapper extends Mapper<RedPacketDetails> {
}