package com.huijava.superiorjavablogs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huijava.superiorjavablogs.common.result.ResultModel;
import com.huijava.superiorjavablogs.entity.WxUsers;
import com.huijava.superiorjavablogs.service.WxUsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * WxUsersController
 *
 * @author chenhaoxiang
 * @date 2019-02-01 01:41:24
 */
@Controller
@RequestMapping("wx/users")
public class WxUsersController {
    @Resource
    private WxUsersService wxUsersService;

    /**
     * 添加WxUsers
     *
     * @param wxUsers 对象
     * @return ResultModel统一响应结果
     */
    @PostMapping("add")
    @ResponseBody
    public ResultModel add(WxUsers wxUsers) {
        wxUsersService.insert(wxUsers);
        return ResultModel.success();
    }

    /**
     * 根据ID进行删除
     *
     * @param id 主键
     * @return ResultModel统一响应结果
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultModel delete(@RequestParam Integer id) {
        wxUsersService.deleteById(id);
        return ResultModel.success();
    }

    /**
     * 根据ID进行修改WxUsers对象
     *
     * @param wxUsers 对象中必须有ID主键
     * @return ResultModel统一响应结果
     */
    @PostMapping("update")
    @ResponseBody
    public ResultModel update(WxUsers wxUsers) {
        wxUsersService.updateById(wxUsers);
        return ResultModel.success();
    }

    /**
     * 查询详情
     *
     * @param id 主键
     * @return ResultModel统一响应结果
     */
    @PostMapping("detail")
    @ResponseBody
    public ResultModel detail(@RequestParam Integer id) {
        WxUsers wxUsers = wxUsersService.selectById(id);
        return ResultModel.success(wxUsers);
    }

    /**
     * 分页查询
     *
     * @param page 当前页 默认0 不分页
     * @param size 每页的条数 默认为0 查询所有
     * @return ResultModel统一响应结果
     */
    @PostMapping("list")
    @ResponseBody
    public ResultModel list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<WxUsers> list = wxUsersService.selectAll();
        PageInfo<WxUsers> pageInfo = new PageInfo<>(list);
        return ResultModel.success(pageInfo);
    }
}
