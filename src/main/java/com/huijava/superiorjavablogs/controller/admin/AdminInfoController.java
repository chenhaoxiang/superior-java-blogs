/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 管理员信息Controller层
 *
 * @author chenhx
 * @version AdminInfoController.java, v 0.1 2018-10-04 下午 7:34
 */
@Controller
@RequestMapping("admin/info")
@Slf4j
public class AdminInfoController {

    /**
     * 个人信息
     *
     * @return
     */
    @GetMapping("profile")
    public ModelAndView profile(Model model) {
        log.info("跳转到个人信息页面...");
        model.addAttribute("pageTable1", "pageBolg");
        model.addAttribute("pageTable2", "pageBolgInsertView");
        return new ModelAndView("admin/info/profile");
    }

    /**
     * 修改密码
     *
     * @return
     */
    @PostMapping("updatePassword")
    public ModelAndView updatePassword(Model model, String oldPassword
            , String newPassword) {
        log.info("跳转到修改密码页面...");
        model.addAttribute("pageTable1", "pageBolg");
        model.addAttribute("pageTable2", "pageBolgInsertView");
        return new ModelAndView("admin/info/profile");
    }


}