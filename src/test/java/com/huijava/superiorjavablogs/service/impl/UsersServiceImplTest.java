/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.service.impl;

import com.huijava.superiorjavablogs.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenhx
 * @version UsersServiceImplTest.java, v 0.1 2019-02-25 23:56 chenhx
 */
public class UsersServiceImplTest extends BaseTest {

    @Autowired
    private UsersServiceImpl usersServiceImpl;


    @Test
    public void selectUsersCountByUserName() {

        System.out.println(usersServiceImpl.selectUsersCountByUserName("admin"));
        System.out.println(usersServiceImpl.selectUsersCountByUserName("admin1"));
        System.out.println(usersServiceImpl.selectUsersCountByUserName("admin2"));
        //----------- Arrange -----------//


        //-----------   Act   -----------//


        //-----------  Assert -----------//
    }
}