package com.huijava.superiorjavablogs.service;

import com.huijava.superiorjavablogs.BaseTest;
import com.huijava.superiorjavablogs.entity.Users;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersServiceTest extends BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersServiceTest.class);
    @Autowired
    private UsersService usersService;

    @Test
    public void selectUsersByUserName() {
        LOGGER.info("user={}", usersService.selectUsersByUserName("admin"));
    }

    @Test
    public void registerAdmin() {
        Users usersForm = new Users();
        usersForm.setUsername("123");
        usersForm.setPassword("asfdgdfgdffs");
        usersForm.setNikeName("谙忆");
        usersForm.setEmail("1234345@qq.com");
        LOGGER.info("usersForm:{}", usersForm);
        LOGGER.info("注册管理员:{}", usersService.registerAdmin(usersForm));
        LOGGER.info("usersForm:{}", usersForm);
    }

    @Test
    public void updatePassword() {
        Users usersForm = new Users();
        usersForm.setUsername("user");
        usersForm.setPassword("1234");
        LOGGER.info("修改密码:{}", usersService.updatePassword(usersForm));
    }

}