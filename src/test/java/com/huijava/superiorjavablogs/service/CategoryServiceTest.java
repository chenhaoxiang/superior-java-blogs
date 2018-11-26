/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.service;

import com.huijava.superiorjavablogs.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenhx
 * @version CategoryServiceTest.java, v 0.1 2018-11-26 下午 10:05 chenhx
 */
public class CategoryServiceTest extends BaseTest {

    @Autowired
    private CategoryService categoryService;


    @Test
    public void selectAllByStatus() {
        System.out.println(categoryService.selectAllByStatus(0));
    }
}