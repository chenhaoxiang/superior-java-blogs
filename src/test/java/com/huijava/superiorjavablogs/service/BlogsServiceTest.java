package com.huijava.superiorjavablogs.service;

import com.huijava.superiorjavablogs.BaseTest;
import com.huijava.superiorjavablogs.entity.Blogs;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BlogsServiceTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogsServiceTest.class);
    @Autowired
    private BlogsService blogsService;

    @Test
    public void selectTagsExtByTagsName() {
    }

    @Test
    public void updateBlogs() {
        List<Blogs> blogsList = blogsService.selectAll();
        for (Blogs blogs : blogsList) {
            blogs.setSummary(blogs.getSummary().replace("\n", ""));
//            blogs.setContent(blogs.getContent().replace("\r\n","<br/>").replace("\n","<br>"));
            blogs.setContent(blogs.getContent().replace("<br>", "\n").replace("<br/>", "\r\n"));
            blogsService.updateById(blogs);
            LOGGER.debug("blogs={}", blogs);
        }
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
    public void selectNewBlogsDTOByNumber() {
    }

    @Test
    public void selectNewBlogsByNumber() {
    }

    @Test
    public void selectBlogsByTitleKeyword() {
    }
}