package com.huijava.superiorjavablogs.mapper;

import com.huijava.superiorjavablogs.entity.Tags;

public interface TagsMapperExt {
    Tags selectTagsByName(String tagsName);
}