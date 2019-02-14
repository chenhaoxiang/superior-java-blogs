package com.huijava.superiorjavablogs.mapper;

import com.huijava.superiorjavablogs.common.base.Mapper;
import com.huijava.superiorjavablogs.entity.Category;
import org.apache.ibatis.annotations.CacheNamespaceRef;

@CacheNamespaceRef(CategoryMapper.class)
public interface CategoryMapper extends Mapper<Category> {
}