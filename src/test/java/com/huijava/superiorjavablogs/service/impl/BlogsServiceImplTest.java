package com.huijava.superiorjavablogs.service.impl;

import com.huijava.superiorjavablogs.BaseTest;
import com.huijava.superiorjavablogs.service.BlogsService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BlogsServiceImplTest extends BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlogsServiceImplTest.class);
    @Autowired
    private BlogsService blogsService;

    @Test
    public void selectTagsExtByTagsName() {
        LOGGER.info("======================={}", blogsService.selectTagsExtByTagsName("Java"));
    }

    @Test
    public void insertBlog() {
    }

    @Test
    public void getBlogsByBlogToken() {
    }

    @Test
    public void selectBlogsByDaysAndNumber() {
    }

    @Test
    public void selectNewestBlogsDTOByNumber() {
    }

    @Test
    public void selectBlogsByTitleKeyword() {
    }

    @Test
    public void addBlogsViewsOne() {
    }


}