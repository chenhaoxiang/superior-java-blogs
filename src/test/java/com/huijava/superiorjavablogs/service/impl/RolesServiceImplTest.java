/**
 * fshows.com
 * Copyright (C) 2013-2019 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.service.impl;

import com.huijava.superiorjavablogs.BaseTest;
import com.huijava.superiorjavablogs.entity.Roles;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chenhx
 * @version RolesServiceImplTest.java, v 0.1 2019-02-26 00:44 chenhx
 */
public class RolesServiceImplTest extends BaseTest {

    @Autowired
    private RolesServiceImpl rolesServiceImpl;


    @Test
    public void insertSelective() {
        Roles roles = new Roles();
        roles.setName("test");
        rolesServiceImpl.insertSelective(roles);
        System.out.println(roles);
        //----------- Arrange -----------//


        //-----------   Act   -----------//


        //-----------  Assert -----------//
    }
}