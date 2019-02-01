package com.huijava.superiorjavablogs.service.impl;

import com.huijava.superiorjavablogs.BaseTest;
import com.huijava.superiorjavablogs.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CategoryServiceImplTest extends BaseTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void selectAllByStatus() {
        log.info("{}", categoryService.selectAllByStatus(0));
    }
}