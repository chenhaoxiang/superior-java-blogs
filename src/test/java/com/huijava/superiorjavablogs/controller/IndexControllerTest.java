package com.huijava.superiorjavablogs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huijava.superiorjavablogs.BaseTest;
import com.huijava.superiorjavablogs.entity.Blogs;
import com.huijava.superiorjavablogs.service.BlogsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public class IndexControllerTest extends BaseTest {

    @Autowired
    private BlogsService blogsService;

    /**
     * 条件分页查询
     */
    @Test
    public void blogs() {
        Example example = new Example(Blogs.class);
        example.createCriteria().andCondition("status = 0");
        ////注意用的是类中的属性，不是数据库中的属性
        example.orderBy("id").desc();
        /**
         * Mapper接口方式的调用，推荐这种使用方式。
         */
        PageHelper.startPage(2, 2);
        List<Blogs> blogsList = blogsService.selectByExample(example);
        System.out.println("============================" + blogsList);

    }

    @Test
    public void blogs2() {
        Example example = new Example(Blogs.class);
        example.createCriteria().andCondition("status = 0");
        ////注意用的是类中的属性，不是数据库中的属性
        example.orderBy("id").desc();
        //排除文章内容
        example.excludeProperties("content");
        PageInfo pageInfo = PageHelper.startPage(2, 2).doSelectPageInfo(
                () -> blogsService.selectByExample(example));
        System.out.println("============================" + pageInfo);
    }

    @Test
    public void blogs3() {
        Example example = new Example(Blogs.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andCondition("status = 0");
        Condition condition = new Condition(Blogs.class);
        ////注意用的是类中的属性，不是数据库中的属性
        condition.orderBy("id").desc();
        condition.and(criteria);
        condition.excludeProperties("content");
        PageInfo pageInfo = PageHelper.startPage(2, 2).doSelectPageInfo(
                () -> blogsService.selectByCondition(condition));
        System.out.println("============================" + pageInfo);
    }

}