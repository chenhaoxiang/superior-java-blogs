package com.huijava.superiorjavablogs.service;

import com.huijava.superiorjavablogs.common.base.Service;
import com.huijava.superiorjavablogs.entity.Category;

import java.util.List;


/**
 * @author chenhaoxiang
 * @date 2018-09-12 18:23:40
 */
public interface CategoryService extends Service<Category> {
    /**
     * 通过状态获取分类
     *
     * @param status
     * @return
     */
    List<Category> selectAllByStatus(Integer status);
}
