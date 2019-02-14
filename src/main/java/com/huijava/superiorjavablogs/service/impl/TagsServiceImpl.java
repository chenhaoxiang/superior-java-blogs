package com.huijava.superiorjavablogs.service.impl;

import com.huijava.superiorjavablogs.common.base.AbstractService;
import com.huijava.superiorjavablogs.entity.Tags;
import com.huijava.superiorjavablogs.mapper.TagsMapper;
import com.huijava.superiorjavablogs.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenhaoxiang
 * @date 2018-09-12 18:23:40
 */
@Service
public class TagsServiceImpl extends AbstractService<Tags> implements TagsService {
    @Autowired
    private TagsMapper tagsMapper;

}
