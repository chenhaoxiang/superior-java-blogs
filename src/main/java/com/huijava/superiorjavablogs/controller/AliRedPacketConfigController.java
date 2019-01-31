package com.huijava.superiorjavablogs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huijava.superiorjavablogs.common.result.ResultModel;
import com.huijava.superiorjavablogs.entity.AliRedPacketConfig;
import com.huijava.superiorjavablogs.service.AliRedPacketConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * AliRedPacketConfigController
 *
 * @author chenhaoxiang
 * @date 2019-02-01 01:41:24
 */
@Controller
@RequestMapping("ali/red/packet/config")
public class AliRedPacketConfigController {
    @Resource
    private AliRedPacketConfigService aliRedPacketConfigService;

    /**
     * 添加AliRedPacketConfig
     *
     * @param aliRedPacketConfig 对象
     * @return ResultModel统一响应结果
     */
    @PostMapping("add")
    @ResponseBody
    public ResultModel add(AliRedPacketConfig aliRedPacketConfig) {
        aliRedPacketConfigService.insert(aliRedPacketConfig);
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
        aliRedPacketConfigService.deleteById(id);
        return ResultModel.success();
    }

    /**
     * 根据ID进行修改AliRedPacketConfig对象
     *
     * @param aliRedPacketConfig 对象中必须有ID主键
     * @return ResultModel统一响应结果
     */
    @PostMapping("update")
    @ResponseBody
    public ResultModel update(AliRedPacketConfig aliRedPacketConfig) {
        aliRedPacketConfigService.updateById(aliRedPacketConfig);
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
        AliRedPacketConfig aliRedPacketConfig = aliRedPacketConfigService.selectById(id);
        return ResultModel.success(aliRedPacketConfig);
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
        List<AliRedPacketConfig> list = aliRedPacketConfigService.selectAll();
        PageInfo<AliRedPacketConfig> pageInfo = new PageInfo<>(list);
        return ResultModel.success(pageInfo);
    }
}
