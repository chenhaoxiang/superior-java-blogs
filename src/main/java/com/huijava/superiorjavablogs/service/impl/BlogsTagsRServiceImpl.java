package com.huijava.superiorjavablogs.service.impl;

import com.huijava.superiorjavablogs.common.base.AbstractService;
import com.huijava.superiorjavablogs.entity.BlogsTagsR;
import com.huijava.superiorjavablogs.mapper.BlogsTagsRMapper;
import com.huijava.superiorjavablogs.service.BlogsTagsRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenhaoxiang
 * @date 2018-09-12 18:23:40
 */
@Service
@Transactional
public class BlogsTagsRServiceImpl extends AbstractService<BlogsTagsR> implements BlogsTagsRService {
    @Autowired
    private BlogsTagsRMapper blogsTagsRMapper;

}
