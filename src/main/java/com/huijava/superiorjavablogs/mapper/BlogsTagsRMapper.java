package com.huijava.superiorjavablogs.mapper;

import com.huijava.superiorjavablogs.common.base.Mapper;
import com.huijava.superiorjavablogs.entity.BlogsTagsR;
import org.apache.ibatis.annotations.CacheNamespaceRef;

@CacheNamespaceRef(BlogsTagsRMapper.class)
public interface BlogsTagsRMapper extends Mapper<BlogsTagsR> {
}