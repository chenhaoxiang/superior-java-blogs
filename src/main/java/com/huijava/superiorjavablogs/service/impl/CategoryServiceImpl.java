package com.huijava.superiorjavablogs.service.impl;

import com.huijava.superiorjavablogs.common.base.AbstractService;
import com.huijava.superiorjavablogs.entity.Category;
import com.huijava.superiorjavablogs.mapper.CategoryMapper;
import com.huijava.superiorjavablogs.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author chenhaoxiang
 * @date 2018-09-12 18:23:40
 */
@Service
@Transactional
public class CategoryServiceImpl extends AbstractService<Category> implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> selectAllByStatus(Integer status) {
        Example categoryExample = new Example(Category.class);
        categoryExample.createCriteria().andCondition("status = " + status);
        return categoryMapper.selectByExample(categoryExample);
    }
}
