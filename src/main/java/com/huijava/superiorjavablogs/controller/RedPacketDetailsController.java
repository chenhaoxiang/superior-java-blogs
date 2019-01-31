package com.huijava.superiorjavablogs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huijava.superiorjavablogs.common.result.ResultModel;
import com.huijava.superiorjavablogs.entity.RedPacketDetails;
import com.huijava.superiorjavablogs.service.RedPacketDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * RedPacketDetailsController
 *
 * @author chenhaoxiang
 * @date 2019-02-01 01:41:24
 */
@Controller
@RequestMapping("red/packet/details")
public class RedPacketDetailsController {
    @Resource
    private RedPacketDetailsService redPacketDetailsService;

    /**
     * 添加RedPacketDetails
     *
     * @param redPacketDetails 对象
     * @return ResultModel统一响应结果
     */
    @PostMapping("add")
    @ResponseBody
    public ResultModel add(RedPacketDetails redPacketDetails) {
        redPacketDetailsService.insert(redPacketDetails);
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
        redPacketDetailsService.deleteById(id);
        return ResultModel.success();
    }

    /**
     * 根据ID进行修改RedPacketDetails对象
     *
     * @param redPacketDetails 对象中必须有ID主键
     * @return ResultModel统一响应结果
     */
    @PostMapping("update")
    @ResponseBody
    public ResultModel update(RedPacketDetails redPacketDetails) {
        redPacketDetailsService.updateById(redPacketDetails);
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
        RedPacketDetails redPacketDetails = redPacketDetailsService.selectById(id);
        return ResultModel.success(redPacketDetails);
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
        List<RedPacketDetails> list = redPacketDetailsService.selectAll();
        PageInfo<RedPacketDetails> pageInfo = new PageInfo<>(list);
        return ResultModel.success(pageInfo);
    }
}
