/**
 * huijava.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huijava.superiorjavablogs.common.base.BaseController;
import com.huijava.superiorjavablogs.common.constant.BlogsConstans;
import com.huijava.superiorjavablogs.common.constant.UserConstans;
import com.huijava.superiorjavablogs.common.enums.BlogFieldEnum;
import com.huijava.superiorjavablogs.common.enums.StatusEnum;
import com.huijava.superiorjavablogs.common.result.ResultModel;
import com.huijava.superiorjavablogs.dto.BlogsDTO;
import com.huijava.superiorjavablogs.dto.UsersDTO;
import com.huijava.superiorjavablogs.entity.Blogs;
import com.huijava.superiorjavablogs.entity.BlogsTagsR;
import com.huijava.superiorjavablogs.entity.Category;
import com.huijava.superiorjavablogs.entity.Roles;
import com.huijava.superiorjavablogs.entity.Tags;
import com.huijava.superiorjavablogs.entity.Users;
import com.huijava.superiorjavablogs.form.UsersForm;
import com.huijava.superiorjavablogs.service.BlogsService;
import com.huijava.superiorjavablogs.service.BlogsTagsRService;
import com.huijava.superiorjavablogs.service.CategoryService;
import com.huijava.superiorjavablogs.service.RolesService;
import com.huijava.superiorjavablogs.service.TagsService;
import com.huijava.superiorjavablogs.service.UsersService;
import com.huijava.superiorjavablogs.util.CookieUtils;
import com.huijava.superiorjavablogs.util.DateUtils;
import com.huijava.superiorjavablogs.util.SessionUtils;
import com.huijava.superiorjavablogs.util.email.SendEmail;
import com.huijava.superiorjavablogs.util.email.impl.EmailConfigImpl;
import com.huijava.superiorjavablogs.util.email.impl.SendEmailCallable;
import com.huijava.superiorjavablogs.util.pass.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 不需要登录即可进行访问
 *
 * @author zhilinzhang
 * @version IndexController.java, v 0.1 2018-07-21 22:00
 */
@Controller
@Slf4j
public class IndexController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private BlogsService blogsService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagsService tagsService;
    @Autowired
    private BlogsTagsRService blogsTagsRService;
    @Autowired
    private EmailConfigImpl emailConfig;
    @Autowired
    private RolesService rolesService;

    /**
     * 30天的毫秒数
     */
    private static Long MONTH_MS = 30 * 24 * 60 * 60 * 1000L;
    /**
     * 邮件验证激活页面
     * @param request
     * @param token
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping("active/email/{time}/{token}/{username}")
    public ResultModel getEmailCode(HttpServletRequest request, @PathVariable("time") Long time, @PathVariable("token") String token,
                                    @PathVariable("username") String username) {
        LOGGER.info("邮箱验证激活页面，token={},username={},time={}", token, username, time);
        if (StringUtils.isBlank(token) || StringUtils.isBlank(username)) {
            return new ResultModel(9999, "参数不正确");
        }
        //判断时间是否在30天内
        Long surplusTime = DateUtils.getLongDateTimeMS() - time;
        if (surplusTime > MONTH_MS) {
            return new ResultModel(9999, "验证链接已过期");
        }
        //查询用户
        Users users = usersService.selectUsersByUserName(username);
        if (users == null) {
            return new ResultModel(9998, "参数不正确,没有该账号");
        }
        if (StatusEnum.ACTIVE.getCode() == users.getStatus()) {
            return new ResultModel(9997, "账号已经激活");
        }
        String realToken = PasswordUtils.getToken(users.getSalt(), users.getPassword(), time);
        if (!realToken.equals(token)) {
            LOGGER.warn("邮箱校验失败，realToken={},token={}", realToken, token);
            return new ResultModel(9996, "校验失败");
        }
        Users updateUsers = new Users();
        updateUsers.setId(users.getId());
        updateUsers.setStatus((byte) StatusEnum.ACTIVE.getCode());
        Integer rows = usersService.updateByPrimaryKeySelective(updateUsers);
        if (rows != 1) {
            LOGGER.error("邮件校验失败，修改用户信息失败,users={},updateUsers={}", users, updateUsers);
            return new ResultModel(9995, "服务器异常,请重试");
        }
        return new ResultModel(200, "激活成功", "欢迎" + username + "使用huijava");
    }



    /**
     * 进行注册
     *
     * @param model
     * @return
     */
    @RequestMapping("toRegister")
    public ModelAndView toRegister(Model model, HttpServletRequest request, @Valid UsersForm usersForm,
                                   BindingResult bindingResult) {
        log.debug("进行注册，当前访问人ip={},注册信息：{}", getIpAddress(request), usersForm);

        model.addAttribute("usersForm", usersForm);
        //进行参数校验
        if (bindingResult.hasErrors()) {
            if (!bindingResult.getAllErrors().isEmpty()) {
                log.info("注册参数格式错误，error:{}", bindingResult.getAllErrors().get(0));
                model.addAttribute("message", bindingResult.getAllErrors().get(0).getDefaultMessage());
                return new ModelAndView("register");
            }
        }
        //判断用户名是否已经存在
        Integer rows = usersService.selectUsersCountByUserName(usersForm.getUsername());
        if (rows != null && rows.equals(1)) {
            model.addAttribute("message", "用户名已被占用");
            return new ModelAndView("register");
        }
        //判断邮箱是否已经存在
        rows = usersService.selectUsersCountByEmail(usersForm.getEmail());
        if (rows != null && rows.equals(1)) {
            model.addAttribute("message", "邮箱已注册");
            return new ModelAndView("register");
        }
        //进行注册
        Users users = new Users();
        users.setUsername(usersForm.getUsername());
        users.setEmail(usersForm.getEmail());
        String salt = PasswordUtils.getSalt();
        users.setSalt(salt);
        users.setPassword(PasswordUtils.getPassword(usersForm.getPassword(), salt));
        users.setStatus((byte) StatusEnum.TO_BE_ACTIVATED.getCode());

        //获取用户rolesId
        String rolesName = "users";
        Roles roles = rolesService.selectByName(rolesName);
        if (roles == null) {
            //插入用户
            roles = new Roles();
            roles.setName(rolesName);
            roles.setComment("普通用户");
            rolesService.insertSelective(roles);
        }
        users.setRolesId(roles.getId());
        users.setNikeName(usersForm.getUsername());
        users.setContributeCount(0L);
        users.setContributeWeight(0L);
        users.setFollowersCount(0);
        rows = usersService.insertSelective(users);
        LOGGER.info("用户进行注册，用户信息：{},影响行数:{}", users, rows);
        if (rows != 1) {
            LOGGER.error("服务器异常，用户进行注册，用户信息:{},影响行数:{}", users, rows);
            model.addAttribute("message", "服务器异常");
            return new ModelAndView("register");
        }

        //发送邮件,用户进行激活
        SendEmail sendEmail = new SendEmail() {
            @Override
            public String getId() {
                return users.getId() + "";
            }

            @Override
            public String getToken() {
                Long time = DateUtils.getLongDateTimeMS();
                return DateUtils.getLongDateTimeMS() + "/" + PasswordUtils.getToken(users.getSalt(), users.getPassword(), DateUtils.getLongDateTimeMS());
            }

            @Override
            public String getName() {
                return users.getUsername();
            }

            @Override
            public String getEmail() {
                return users.getEmail();
            }
        };
        SendEmailCallable sendEmailCallable = new SendEmailCallable(emailConfig, sendEmail);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(sendEmailCallable);
        //加载右边栏数据
        loagTabbableBlogsLost(model, blogsService);

        //重定向到登录页面
        return new ModelAndView("redirect:login");
    }


    /**
     * 进行登录
     *
     * @param model
     * @return
     */
    @RequestMapping("toLogin")
    public ModelAndView toLogin(Model model, HttpServletRequest request,
                                String username, String password) {
        log.debug("进行登录，当前访问人ip={},username={}", getIpAddress(request), username);
        //加载右边栏数据
        loagTabbableBlogsLost(model, blogsService);
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password)) {
            model.addAttribute("message", "账号/密码不能为空");
            return new ModelAndView("login");
        }
        //通过用户名获取用户
        Users users = usersService.selectUsersByUserName(username);
        if (users == null) {
            model.addAttribute("message", "账号/密码错误");
            return new ModelAndView("login");
        }
        //校验密码
        String pass = PasswordUtils.getPassword(password, users.getSalt());
        LOGGER.info("当前登录的用户信息:users={},用户输入的密码:{}", users, pass);
        if (!pass.equals(users.getPassword())) {
            model.addAttribute("message", "账号/密码错误");
            return new ModelAndView("login");
        }

        if (StatusEnum.ACTIVE.getCode() != users.getStatus()) {
            log.info("用户状态不正常，实际对象：{}", users);
            model.addAttribute("message", "账号已被冻结，有问题请联系管理员");
            if (StatusEnum.TO_BE_ACTIVATED.getCode() == users.getStatus()) {
                model.addAttribute("message", "请查看邮箱进行激活账号!");
            }
            return new ModelAndView("login");
        }

        SessionUtils.setAttribute(request, UserConstans.LOGIN_SESSION_NAME, users);
        //重定向到主页
        return new ModelAndView("redirect:index");
    }


    /**
     * 博客搜索
     *
     * @param model
     * @return
     */
    @RequestMapping("blogs/search/{pageNum}")
    public ModelAndView blogsSearch(Model model, String searchTitle
            , @PathVariable("pageNum") Integer pageNum) {
        LOGGER.debug("博客搜索 - searchTitle={},pageNum={}", searchTitle, pageNum);
        if (pageNum < 1) {
            pageNum = 1;
        }
        if (searchTitle.trim().length() == 0) {
            //重定向到主页
            return new ModelAndView("redirect:/");
        }

        //搜索博客 状态正常的 排行前30
        Example example = new Example(Blogs.class);
        example.createCriteria().andCondition("status = 0").andLike("title", "%" + searchTitle + "%");
        //注意用的是类中的属性，不是数据库中的属性
        example.orderBy("id").desc();
        //排除文章内容
        example.excludeProperties("content");
        PageInfo pageInfo = PageHelper.startPage(pageNum, BlogsConstans.BLOGS_PAGE_SIZE).doSelectPageInfo(
                () -> blogsService.selectByExample(example));
        //查询标签
        List<Blogs> blogsList = pageInfo.getList();
        List<BlogsDTO> blogsDTOList = new ArrayList<>();
        Map<Integer, Tags> integerTagsMap = new HashMap<>(64);
        for (Blogs blogs : blogsList) {
            BlogsDTO blogsDTO = new BlogsDTO();
            BeanUtils.copyProperties(blogs, blogsDTO);
            //TODO 后面这里会做成缓存，存到Redis中。也就是博客id对应标签id。
            // 查询标签，设置进去
            Example blogsTagsExample = new Example(BlogsTagsR.class);
            blogsTagsExample.createCriteria().andCondition("blogs_id = " + blogsDTO.getId());
            List<BlogsTagsR> blogsTagsRList = blogsTagsRService.selectByExample(blogsTagsExample);
            StringBuffer tagsStr = new StringBuffer();
            List<Tags> tagsList = new ArrayList<>();
            for (BlogsTagsR blogsTagsR : blogsTagsRList) {
                if (integerTagsMap.containsKey(blogsTagsR.getTagsId())) {
                    tagsList.add(integerTagsMap.get(blogsTagsR.getTagsId()));
                } else {
                    tagsStr.append(",").append(blogsTagsR.getTagsId());
                }
            }
            if (tagsStr.length() > 0) {
                tagsStr = new StringBuffer(tagsStr.substring(1, tagsStr.length()));
                List<Tags> newTagsList = tagsService.selectByIds(tagsStr.toString());
                for (Tags tags : newTagsList) {
                    integerTagsMap.put(tags.getId(), tags);
                }
                tagsList.addAll(newTagsList);
            }
            blogsDTO.setTagsList(tagsList);
            blogsDTOList.add(blogsDTO);
        }
        model.addAttribute("blogsDTOList", blogsDTOList);
        model.addAttribute("pages", pageInfo.getPages());
        model.addAttribute("pageNum", pageInfo.getPageNum());

        //获取所有的分类
        List<Category> categoryList = categoryService.selectAll();
        LOGGER.debug("获取所有的分类 categoryList.size={}", categoryList.size());
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("searchTitle", searchTitle);

        LOGGER.debug("结束搜索....");

        loagTabbableBlogsLost(model, blogsService);
        return new ModelAndView("blog/blogs-search");
    }


    /**
     * 访问博客
     *
     * @param model
     * @return
     */
    @RequestMapping("blogs/{categoryId}/{pageNum}")
    public ModelAndView blogs(Model model, @PathVariable("categoryId") Integer categoryId
            , @PathVariable("pageNum") Integer pageNum) {
        LOGGER.debug("访问博客分类 - categoryId={},pageNum={}", categoryId, pageNum);
        if (pageNum < 1) {
            pageNum = 1;
        }
        String querySql = "";

        if (Integer.compare(categoryId, 0) > 0) {
            querySql = "and category_id=" + categoryId;
        } else {
            categoryId = 0;
        }

        //查询所有博客 状态正常的 排行前30
        Example example = new Example(Blogs.class);
        example.createCriteria().andCondition("status = 0 " + querySql);
        ////注意用的是类中的属性，不是数据库中的属性
        example.orderBy("createTime").desc();
        //排除文章内容
        example.excludeProperties("content");
        PageInfo pageInfo = PageHelper.startPage(pageNum, BlogsConstans.BLOGS_PAGE_SIZE).doSelectPageInfo(
                () -> blogsService.selectByExample(example));
        //查询标签
        List<Blogs> blogsList = pageInfo.getList();
        List<BlogsDTO> blogsDTOList = new ArrayList<>();
        Map<Integer, Tags> integerTagsMap = new HashMap<>(64);
        for (Blogs blogs : blogsList) {
            BlogsDTO blogsDTO = new BlogsDTO();
            BeanUtils.copyProperties(blogs, blogsDTO);
            //TODO 后面这里会做成缓存，存到Redis中。也就是博客id对应标签id。
            // 查询标签，设置进去
            Example blogsTagsExample = new Example(BlogsTagsR.class);
            blogsTagsExample.createCriteria().andCondition("blogs_id = " + blogsDTO.getId());
            List<BlogsTagsR> blogsTagsRList = blogsTagsRService.selectByExample(blogsTagsExample);
            StringBuffer tagsStr = new StringBuffer();
            List<Tags> tagsList = new ArrayList<>();
            for (BlogsTagsR blogsTagsR : blogsTagsRList) {
                if (integerTagsMap.containsKey(blogsTagsR.getTagsId())) {
                    tagsList.add(integerTagsMap.get(blogsTagsR.getTagsId()));
                } else {
                    tagsStr.append(",").append(blogsTagsR.getTagsId());
                }
            }
            if (tagsStr.length() > 0) {
                tagsStr = new StringBuffer(tagsStr.substring(1, tagsStr.length()));
                List<Tags> newTagsList = tagsService.selectByIds(tagsStr.toString());
                for (Tags tags : newTagsList) {
                    integerTagsMap.put(tags.getId(), tags);
                }
                tagsList.addAll(newTagsList);
            }
            blogsDTO.setTagsList(tagsList);
            blogsDTOList.add(blogsDTO);
        }
        model.addAttribute("blogsDTOList", blogsDTOList);
        model.addAttribute("pages", pageInfo.getPages());
        model.addAttribute("pageNum", pageInfo.getPageNum());

        //获取所有的分类
        List<Category> categoryList = categoryService.selectAllByStatus(StatusEnum.ACTIVE.getCode());
        LOGGER.debug("获取所有的分类 categoryList.size={}", categoryList.size());
        model.addAttribute("categoryList", categoryList);

        //设置当前的类
        model.addAttribute("nowCategoryId", categoryId);
        model.addAttribute("pageTable1", "blogsLi");
        LOGGER.debug("结束访问博客分类....");

        loagTabbableBlogsLost(model, blogsService);
        return new ModelAndView("blog/blogs");
    }

    /**
     * 访问文章的排行
     *
     * @param model
     * @return
     */
    @RequestMapping("blogs-rank/{blogField}")
    public ModelAndView blogRank(Model model, @PathVariable("blogField") String blogField) {
        LOGGER.debug("访问文章的排行,blogField={}", blogField);
        BlogFieldEnum blogFieldEnum = BlogFieldEnum.getBlogFieldEnumByFieldName(blogField);
        if (blogFieldEnum == null) {
            blogFieldEnum = BlogFieldEnum.VIEW_COUNT;
        }
        List<BlogsDTO> blogsDTOList = blogsService.selectBlogsDTOByDaysAndNumber(2 * 365, 50, blogFieldEnum);
        //设置当前排行的类目
        model.addAttribute("activeLi", blogFieldEnum.getFieldName());
        model.addAttribute("blogsDTOList", blogsDTOList);
        model.addAttribute("pageTable1", "blogsRankLi");
        model.addAttribute("day", 2 * 365);
        LOGGER.debug("访问文章的排行结束，blogFieldEnum={}", blogFieldEnum);

        loagTabbableBlogsLost(model, blogsService);
        return new ModelAndView("blog/blogs-rank");
    }

    /**
     * 访问具体的文章
     *
     * @param model
     * @return
     */
    @RequestMapping("blogs-details/{blogToken}")
    public ModelAndView blogDetails(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable("blogToken") String blogToken) {
        if (StringUtils.isBlank(blogToken)) {
            log.debug("传过来的blogToken为空");
            return new ModelAndView("index");
        }
        //获取具体的博客文章
        BlogsDTO blogsDTO = blogsService.getBlogsDTOByBlogToken(blogToken);
        LOGGER.debug("获取具体的博客文章 -> blogsDTO={}", blogsDTO);
        if (blogsDTO == null) {
            log.debug("通过blogToken[{}]查询的blogs为空", blogToken);
            return new ModelAndView("redirect:/");
        }
        if (StatusEnum.ACTIVE.getCode() != blogsDTO.getStatus()) {
            log.debug("blogsDTO[{}]被删除或者在审核中", blogsDTO);
            return new ModelAndView("redirect:/");
        }

        String value = CookieUtils.getCookie(request, blogToken);
        //TODO 另外需要一个IP判断
        if (com.huijava.superiorjavablogs.util.StringUtils.isEmpty(value)) {
            CookieUtils.setCookie(response, blogToken, "1", 60 * 60);
            //增加博客的访问量  异步进行增加
            blogsService.addBlogsViewsOne(blogToken);
        }

        //查询博客的分类
        Category category = categoryService.selectById(blogsDTO.getCategoryId());
        LOGGER.debug("获取具体的博客文章 -> category={}", category);
        blogsDTO.setCategory(category);

        model.addAttribute("blogs", blogsDTO);
        Users users = usersService.selectById(blogsDTO.getCreateId());
        LOGGER.debug("获取具体的博客文章 -> users={}", users);
        UsersDTO usersDTO = new UsersDTO();
        BeanUtils.copyProperties(users, usersDTO);
        LOGGER.debug("获取具体的博客文章 -> usersDTO={}", usersDTO);
        model.addAttribute("usersDTO", usersDTO);
        model.addAttribute("pageTable1", "blogsLi");

        loagTabbableBlogsLost(model, blogsService);
        return new ModelAndView("blog/blogs-details");
    }

    /**
     * 访问主页
     *
     * @param model
     * @return
     */
    @RequestMapping({"index", "", "/"})
    public ModelAndView index(Model model) {
        //获取最新的文章  16篇
        List<BlogsDTO> newBlogsList = blogsService.selectNewBlogsDTOByNumber(20, StatusEnum.ACTIVE.getCode());
        log.debug("查询出最新的博客:newBlogsList.size={}", newBlogsList.size());
        model.addAttribute("newBlogsList", newBlogsList);
        //获取访问量排行的文章 - 180日内 16篇文章

        loagTabbableBlogsLost(model, blogsService);
        model.addAttribute("pageTable1", "indexLi");
        return new ModelAndView("index");
    }

    /**
     * 登录页面
     *
     * @param model
     * @return
     */
    @RequestMapping("login")
    public ModelAndView login(Model model, HttpServletRequest request) {
        log.debug("登录页面，当前访问人ip={}", getIpAddress(request));

        //加载右边栏数据
        loagTabbableBlogsLost(model, blogsService);

        return new ModelAndView("login");
    }


    /**
     * 注册页面
     *
     * @param model
     * @return
     */
    @RequestMapping("register")
    public ModelAndView register(Model model, HttpServletRequest request) {
        log.debug("注册页面，当前访问人ip={}", getIpAddress(request));

        model.addAttribute("usersForm", new UsersForm());
        //加载右边栏数据
        loagTabbableBlogsLost(model, blogsService);

        return new ModelAndView("register");
    }



    /**
     * 进行测试
     *
     * @param map
     * @return
     */
    @RequestMapping("test")
    public ModelAndView test(Map<String, Object> map) {
        map.put("name", "test");
        map.put("age", 21);
        log.info("测试");
        log.error("测试日志发送邮件,map:{}", map);
        return new ModelAndView("test", map);
    }
}