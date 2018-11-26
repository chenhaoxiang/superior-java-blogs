package com.huijava.superiorjavablogs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huijava.superiorjavablogs.common.result.ResultModel;
import com.huijava.superiorjavablogs.entity.BlogsTagsR;
import com.huijava.superiorjavablogs.service.BlogsTagsRService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * BlogsTagsRController
 *
 * @author chenhaoxiang
 * @date 2018-09-12 18:23:40
 */
@Controller
@RequestMapping("admin/blogs/tags/r")
public class BlogsTagsRController {
    @Resource
    private BlogsTagsRService blogsTagsRService;

    /**
     * 添加BlogsTagsR
     *
     * @param blogsTagsR 对象
     * @return ResultModel统一响应结果
     */
    @PostMapping("add")
    @ResponseBody
    public ResultModel add(BlogsTagsR blogsTagsR) {
        blogsTagsRService.insert(blogsTagsR);
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
        blogsTagsRService.deleteById(id);
        return ResultModel.success();
    }

    /**
     * 根据ID进行修改BlogsTagsR对象
     *
     * @param blogsTagsR 对象中必须有ID主键
     * @return ResultModel统一响应结果
     */
    @PostMapping("update")
    @ResponseBody
    public ResultModel update(BlogsTagsR blogsTagsR) {
        blogsTagsRService.updateById(blogsTagsR);
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
        BlogsTagsR blogsTagsR = blogsTagsRService.selectById(id);
        return ResultModel.success(blogsTagsR);
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
        List<BlogsTagsR> list = blogsTagsRService.selectAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultModel.success(pageInfo);
    }
}
