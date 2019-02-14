/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.mapper;

import com.huijava.superiorjavablogs.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenhx
 * @version BlogsMapperExtTest.java, v 0.1 2019-02-15 02:08 chenhx
 */
@Slf4j
public class BlogsMapperExtTest extends BaseTest {

    @Autowired
    private BlogsMapperExt blogsMapperExt;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void selectBlogsByTitleKeywordTest() {

        //----------- Arrange -----------//
        log.info("开始查询...");
        SqlSession sqlSession1 = sqlSessionFactory.openSession();

        SqlSession sqlSession3 = sqlSessionFactory.openSession();

        BlogsMapperExt blogsMapper = sqlSession1.getMapper(BlogsMapperExt.class);
        long s = System.currentTimeMillis();
        log.info("{}", blogsMapper.selectBlogsByTitleKeyword("spring").size());
        long e = System.currentTimeMillis();
        //这里执行关闭操作，将sqlsession中的数据写到二级缓存区域
        sqlSession1.close();
        log.info("第一次的查询时间:{}ms", (e - s));

        //-----------   Act   -----------//

        //-----------  Assert -----------//

        BlogsMapperExt blogsMapper3 = sqlSession3.getMapper(BlogsMapperExt.class);

        s = System.currentTimeMillis();
        log.info("{}", blogsMapper3.selectBlogsByTitleKeyword("spring").size());
        e = System.currentTimeMillis();
        sqlSession3.close();
        log.info("第三次的查询时间:{}ms", (e - s));

        s = System.currentTimeMillis();
        log.info("{}", blogsMapperExt.selectBlogsByTitleKeyword("spring").size());
        e = System.currentTimeMillis();

        log.info("第四次查询的时间:{}ms", (e - s));
    }
}