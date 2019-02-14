package com.huijava.superiorjavablogs.mapper;

import com.huijava.superiorjavablogs.common.base.Mapper;
import com.huijava.superiorjavablogs.entity.Blogs;
import org.apache.ibatis.annotations.CacheNamespaceRef;

/**
 * @CacheNamespaceRef(BlogsMapper.class) 开启二级缓存，配合xml中的cache标签使用
 */
@CacheNamespaceRef(BlogsMapper.class)
public interface BlogsMapper extends Mapper<Blogs> {
}