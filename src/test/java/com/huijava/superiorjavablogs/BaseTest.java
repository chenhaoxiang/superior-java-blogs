/**
 * huijava.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author chenhx
 * @version BaseTest.java, v 0.1 2018-09-12 下午 8:14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@TestConfiguration("classpath:application.properties")
//@ComponentScan(basePackages = {"com.huijava.**","me.chanjar.**"})
@Slf4j
public class BaseTest {
    @Test
    public void testEmail() {
        log.error("测试发送erroer邮件");
    }
}