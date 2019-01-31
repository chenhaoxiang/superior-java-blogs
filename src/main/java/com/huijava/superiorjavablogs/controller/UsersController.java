package com.huijava.superiorjavablogs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huijava.superiorjavablogs.common.base.BaseController;
import com.huijava.superiorjavablogs.common.constant.BlogsConstans;
import com.huijava.superiorjavablogs.dto.UsersDTO;
import com.huijava.superiorjavablogs.entity.Blogs;
import com.huijava.superiorjavablogs.entity.Users;
import com.huijava.superiorjavablogs.service.BlogsService;
import com.huijava.superiorjavablogs.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Example;

/**
 * UsersController
 *
 * @author chenhaoxiang
 * @date 2018-09-12 18:23:40
 */
@Controller
@RequestMapping("users")
public class UsersController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);
    @Autowired
    private UsersService usersService;
    @Autowired
    private BlogsService blogsService;

    /**
     * 用户个人信息中心
     *
     * @param model
     * @param username  用户名 小写英文/数字
     * @param fieldName 博客，粉丝，评论
     * @param pageNum   当前页
     * @return
     */
    @RequestMapping("/{username}")
    public ModelAndView blogRank(Model model, @PathVariable("username") String username
            , @RequestParam(value = "fieldName", defaultValue = "blogs") String fieldName
            , @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        LOGGER.debug("访问用户个人信息中心,username={}", username);
        //查询用户
        Users users = usersService.selectUsersByUserName(username);
        UsersDTO usersDTO = new UsersDTO();
        BeanUtils.copyProperties(users, usersDTO);
        model.addAttribute("usersDTO", usersDTO);

        //查询该用户发表的博客
        if (fieldName.equals("blogs")) {
            //查询博客
            //搜索博客 状态正常的 排行前30
            Example example = new Example(Blogs.class);
            example.createCriteria().andCondition("status = 0").andCondition("create_id = " + usersDTO.getId());
            //注意用的是类中的属性，不是数据库中的属性
            example.orderBy("id").desc();
            //排除文章内容
            example.excludeProperties("content");
            PageInfo pageInfo = PageHelper.startPage(pageNum, BlogsConstans.BLOGS_PAGE_SIZE).doSelectPageInfo(
                    () -> blogsService.selectByExample(example));
            model.addAttribute("blogsDTOList", pageInfo.getList());
            model.addAttribute("pages", pageInfo.getPages());
            model.addAttribute("pageNum", pageInfo.getPageNum());
            model.addAttribute("blogsCount", pageInfo.getTotal());
        }

        loagTabbableBlogsLost(model, blogsService);
        model.addAttribute("fieldName", fieldName);
        return new ModelAndView("user/user-info");
    }

}
