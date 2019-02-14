package com.huijava.superiorjavablogs.service.impl;

import com.huijava.superiorjavablogs.BaseTest;
import com.huijava.superiorjavablogs.entity.Blogs;
import com.huijava.superiorjavablogs.mapper.BlogsMapper;
import com.huijava.superiorjavablogs.service.BlogsService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

public class BlogsServiceImplTest extends BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlogsServiceImplTest.class);
    @Autowired
    private BlogsService blogsService;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

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


    @Test
    public void selectAllDescIdExContentAndSummary() {

        //----------- Arrange -----------//
        LOGGER.info("开始查询...");
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();

        SqlSession sqlSession3 = sqlSessionFactory.openSession();


        Example example = new Example(Blogs.class);
        //注意用的是类中的属性，不是数据库中的属性
        example.orderBy("id").desc();
        example.excludeProperties("content", "summary");
        BlogsMapper blogsMapper = sqlSession1.getMapper(BlogsMapper.class);
        long s = System.currentTimeMillis();
        LOGGER.info("{}", blogsMapper.selectByExample(example).size());
        long e = System.currentTimeMillis();
        //这里执行关闭操作，将sqlsession中的数据写到二级缓存区域
        sqlSession1.close();
        LOGGER.info("第一次的查询时间:{}ms", (e - s));

        //-----------   Act   -----------//

//        Example example2 = new Example(Blogs.class);
//        //注意用的是类中的属性，不是数据库中的属性
//        example2.orderBy("id").desc();
//        example2.excludeProperties("content", "summary");
//        BlogsMapper blogsMapper2 = sqlSession2.getMapper(BlogsMapper.class);
//
//        Blogs blogs = new Blogs();
//        blogs = blogsMapper2.selectByPrimaryKey(2);
//        s = System.currentTimeMillis();
//        LOGGER.info("{}", blogsMapper2.updateByPrimaryKey(blogs));
//        e = System.currentTimeMillis();
//
//        //执行提交，清空二级缓存
//        sqlSession2.commit();
//        sqlSession2.close();
//        LOGGER.info("第二次的查询时间:{}ms",(e-s));

        //-----------  Assert -----------//


        Example example3 = new Example(Blogs.class);
        //注意用的是类中的属性，不是数据库中的属性
        example3.orderBy("id").desc();
        example3.excludeProperties("content", "summary");
        BlogsMapper blogsMapper3 = sqlSession3.getMapper(BlogsMapper.class);

        s = System.currentTimeMillis();
        LOGGER.info("{}", blogsMapper3.selectByExample(example3).size());
        e = System.currentTimeMillis();
        sqlSession3.close();
        LOGGER.info("第三次的查询时间:{}ms", (e - s));


    }
}