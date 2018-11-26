/**
 * huijava.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.controller.admin;

import com.huijava.superiorjavablogs.common.constant.UserConstans;
import com.huijava.superiorjavablogs.common.enums.StatusEnum;
import com.huijava.superiorjavablogs.common.result.ResultModel;
import com.huijava.superiorjavablogs.entity.Users;
import com.huijava.superiorjavablogs.service.UsersService;
import com.huijava.superiorjavablogs.util.SessionUtils;
import com.huijava.superiorjavablogs.util.pass.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * 登录
 *
 * @author chenhx
 * @version LoginController.java, v 0.1 2018-07-25 下午 2:47
 */
@Controller
@RequestMapping("admin")
@Slf4j
public class LoginController {
    @Autowired
    private UsersService usersService;


    @GetMapping({"index", ""})
    public ModelAndView index(Model model) {
        model.addAttribute("pageTable1", "pageIndex");
        return new ModelAndView("admin/index");
    }

    /**
     * 登录
     *
     * @return
     */
    @GetMapping("login")
    public String login() {
        //转到登录页面
        return "admin/login";
    }

    /**
     * 在对象参数上使用@Valid 注解会直接判断是否符合.BindingResult必须紧跟在Valid注解参数后
     * 进行提交登录
     *
     * @param users
     * @param bindingResult
     * @param request
     * @return
     */
    @PostMapping("loginRequest")
    public ModelAndView requestLogin(Map<String, Object> map, @Valid Users users
            , BindingResult bindingResult
            , HttpServletRequest request) {
        log.info("进行登录:{}", users);
        //进行参数校验
        if (bindingResult.hasErrors()) {
            if (!bindingResult.getAllErrors().isEmpty()) {
                ObjectError error = bindingResult.getAllErrors().get(0);
                log.info("登录参数格式错误，error:{}", error);
                map.put("message", "登录参数格式错误:" + error.getDefaultMessage());
                return new ModelAndView("admin/login", map);
            }
        }
        //参数验证
//        ValidateResult validate = ValidateUtils.validate(users);
//        if (!validate.isSuccess()) {
//            log.debug("登录参数格式错误，validate:{}", validate);
//            map.put(ResultEnum.ERROR.getKey(),"登录参数格式错误:"+validate);
//            return new ModelAndView("admin/login",map);
//        }

        //防止ip恶意登录

        Users realUser = usersService.selectUsersByUserName(users.getUsername());
        if (realUser == null) {
            log.info("用户名不存在，{}", users);
            map.put("message", "用户名/密码错误");
            return new ModelAndView("admin/login", map);
        }
        String pass = PasswordUtils.getPassword(users.getPassword(), realUser.getSalt());
        if (!pass.equals(realUser.getPassword())) {
            log.info("密码不正确，表单对象：{}，实际对象：{}", users, realUser);
            map.put("message", "用户名/密码错误");
            return new ModelAndView("admin/login");
        }
        if (StatusEnum.ACTIVE.getCode() != realUser.getStatus()) {
            log.info("管理员被冻结，表单对象：{}，实际对象：{}", users, realUser);
            map.put("message", "账号被冻结");
            return new ModelAndView("admin/login", map);
        }
        SessionUtils.setAttribute(request, UserConstans.LOGIN_SESSION_NAME, realUser);
        //重定向到主页
        return new ModelAndView("redirect:index");
    }

    /**
     * 添加管理员
     *
     * @param users
     * @param bindingResult
     * @return
     */
    @PostMapping("addAdmin")
    public ResultModel addAdmin(@Valid Users users, BindingResult bindingResult) {
        log.info("添加管理员,usersForm:{}", users);
        //进行参数校验
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info("添加管理员参数格式错误，error:{}", error);
                return ResultModel.error("登录参数格式错误:" + error.getDefaultMessage());
            }
        }
        //判断用户名是否有
        if (usersService.selectUsersByUserName(users.getUsername()) != null) {
            return ResultModel.error("用户名已经被使用,请修改用户名");
        }
        try {
            if (usersService.registerAdmin(users).equals(1)) {
                return ResultModel.success("添加成功");
            }
        } catch (Exception e) {
            log.error("添加管理员出现异常:{}" + e.getMessage());
        }
        return ResultModel.error("添加失败,请稍后重试");
    }
}