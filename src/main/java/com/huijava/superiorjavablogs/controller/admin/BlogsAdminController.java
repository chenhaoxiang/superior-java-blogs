/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.controller.admin;

import com.google.common.collect.Lists;
import com.huijava.superiorjavablogs.common.base.BaseController;
import com.huijava.superiorjavablogs.common.constant.BlogsConstans;
import com.huijava.superiorjavablogs.common.enums.BlogOriginalEnum;
import com.huijava.superiorjavablogs.common.enums.ColorNameEnum;
import com.huijava.superiorjavablogs.common.enums.StatusEnum;
import com.huijava.superiorjavablogs.common.exception.ServiceException;
import com.huijava.superiorjavablogs.common.result.ResultModel;
import com.huijava.superiorjavablogs.entity.Blogs;
import com.huijava.superiorjavablogs.entity.Category;
import com.huijava.superiorjavablogs.entity.Tags;
import com.huijava.superiorjavablogs.entity.Users;
import com.huijava.superiorjavablogs.form.BlogForm;
import com.huijava.superiorjavablogs.service.BlogsService;
import com.huijava.superiorjavablogs.service.CategoryService;
import com.huijava.superiorjavablogs.util.DefaultBeanUtils;
import com.huijava.superiorjavablogs.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 后台的博客管理
 *
 * @author chenhx
 * @version BlogsAdminController.java, v 0.1 2018-09-14 下午 8:39
 */
@Controller
@RequestMapping("admin/blogs")
@Slf4j
public class BlogsAdminController extends BaseController {


    @Autowired
    private BlogsService blogsService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 博客列表页面
     *
     * @return
     */
    @GetMapping("listAll")
    public ModelAndView listAll(Model model, @ModelAttribute("cleanLocalStorage") String cleanLocalStorage) {
        log.info("博客列表页面...cleanLocalStorage={}", cleanLocalStorage);
        model.addAttribute("pageTable1", "pageBolg");
        model.addAttribute("pageTable2", "pageBolgListAll");

        List<Blogs> blogsList = blogsService.selectAllDescIdExContentAndSummary();
        log.info("获取的博客条数:{}", blogsList.size());
        model.addAttribute("blogsList", blogsList);
        model.addAttribute("cleanLocalStorage", cleanLocalStorage);
        return new ModelAndView("admin/blogs/blog-list");
    }

    /**
     * 修改博客状态
     *
     * @return
     */
    @PostMapping("update/status")
    @ResponseBody
    public ResultModel updateStatus(Integer status, Integer id) {
        log.debug("修改博客状态...status={},id={}", status, id);
        Blogs blogs = null;
        try {
            blogs = new Blogs();
            blogs.setId(id);
            blogs.setStatus(new Byte(status.toString()));
            blogsService.updateById(blogs);
        } catch (Exception e) {
            return ResultModel.error();
        }
        log.info("修改博客状态成功，blogs={}", blogs);
        return ResultModel.success(blogs.getStatus());
    }

    /**
     * 跳转到添加博客页面
     *
     * @return
     */
    @GetMapping("insertView")
    public ModelAndView insert(Model model) {
        log.info("跳转到添加博客页面...");
        model.addAttribute("pageTable1", "pageBolg");
        model.addAttribute("pageTable2", "pageBolgInsertView");
        //获取所有的分类
        List<Category> categoryExtList = categoryService.selectAll();
        model.addAttribute("categoryExtList", categoryExtList);
        return new ModelAndView("admin/blogs/blog-insert");
    }

    /**
     * 添加博客
     *
     * @return
     */
    @PostMapping("insertPost")
    public RedirectView insertPost(HttpServletRequest request, @Valid BlogForm blogForm
            , BindingResult bindingResult, RedirectAttributes attributes) {
        log.info("添加博客blogForm={}", blogForm);
        //进行参数校验
        if (bindingResult.hasErrors()) {
            if (!bindingResult.getAllErrors().isEmpty()) {
                log.info("添加博客参数格式错误，error:{}", bindingResult.getAllErrors().get(0));
                throw new ServiceException("登录参数格式错误，error:" + bindingResult.getAllErrors().get(0));
            }
        }

        Users users = getLoginUsers(request);
        List<Tags> tagsList = Lists.newArrayList();
        //数据库已经存在的标签
        List<Tags> oldTagsList = Lists.newArrayList();
        if (blogForm.getTags().trim().length() > 0) {
            String[] tagsNames = blogForm.getTags().split(";");
            for (int i = 0; i < (tagsNames.length > 5 ? 5 : tagsNames.length); i++) {
                if (org.apache.commons.lang3.StringUtils.isEmpty(tagsNames[i].trim())) {
                    continue;
                }
                //查询标签名
                Tags tagsExt = blogsService.selectTagsExtByTagsName(tagsNames[i]);
                if (tagsExt == null) {
                    Tags tags = new Tags();
                    tags.setName(tagsNames[i]);
                    tags.setCreateId(users.getId());
                    tags.setUpdateId(users.getId());
                    //随机设置一个标签颜色
                    tags.setColorName(ColorNameEnum.getRandomColorName());
                    DefaultBeanUtils.defaultTags(tags);
                    tagsList.add(tags);
                } else {
                    oldTagsList.add(tagsExt);
                }
            }
        }
        Blogs blogs = new Blogs();
        BeanUtils.copyProperties(blogForm, blogs);
        //将换行转换为br \r\n -> <br/>
//        blogs.setContent(blogs.getContent().replace("\r\n","<br/>").replace("\n","<br>"));
        blogs.setBlogToken(StringUtils.getBlogToken());
        if (blogForm.getAuthor().trim().length() == 0 && blogForm.getOriginalUrl().trim().length() == 0) {
            //原创文章
            blogs.setOriginal(new Byte(BlogOriginalEnum.ORIGINAL.getCode().toString()));
            blogs.setAuthor(users.getNikeName());
            if (StringUtils.isEmpty(users.getNikeName())) {
                blogs.setAuthor(users.getUsername());
            }
            blogs.setOriginalUrl(BlogsConstans.BLOGS_ORIGINAL_URL + blogs.getBlogToken());
        } else {
            //转载文章
            blogs.setOriginal(new Byte(BlogOriginalEnum.REPRINT.getCode().toString()));
        }
        blogs.setCreateId(users.getId());
        blogs.setUpdateId(users.getId());
        blogsService.insertBlog(blogs, tagsList, oldTagsList, (byte) StatusEnum.WAITREVIEW.getCode());
        log.info("插入博客成功，blogs={}", blogs);
        //清除博客缓存需要的参数
        attributes.addFlashAttribute("cleanLocalStorage", "1");
        //重定向传值
        return new RedirectView("listAll");
    }


}