/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs;

import com.google.common.collect.Lists;
import com.huijava.superiorjavablogs.common.constant.BlogsConstans;
import com.huijava.superiorjavablogs.common.enums.BlogOriginalEnum;
import com.huijava.superiorjavablogs.common.enums.ColorNameEnum;
import com.huijava.superiorjavablogs.common.enums.StatusEnum;
import com.huijava.superiorjavablogs.entity.Blogs;
import com.huijava.superiorjavablogs.entity.Tags;
import com.huijava.superiorjavablogs.entity.Users;
import com.huijava.superiorjavablogs.form.BlogForm;
import com.huijava.superiorjavablogs.service.BlogsService;
import com.huijava.superiorjavablogs.service.UsersService;
import com.huijava.superiorjavablogs.util.DefaultBeanUtils;
import com.huijava.superiorjavablogs.util.FileUtils;
import com.huijava.superiorjavablogs.util.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 批量导入博客发表
 *
 * @author chenhx
 * @version BlogsJob.java, v 0.1 2018-11-27 下午 11:54 chenhx
 */
public class BlogsJobTest extends BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlogsJobTest.class);
    private final static String SUFFIX = ".markdown";
    @Autowired
    private UsersService usersService;
    @Autowired
    private BlogsService blogsService;

    /**
     * 批量导入博客文章
     *
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void publishBlogs() throws IOException, ParseException {
        List<String> files = FileUtils.getFiles("D:\\github\\blog-master\\blog-master\\0000");
        LOGGER.info("文件数量:{}", files.size());
        for (String file : files) {
            //博客文件
            if (file.endsWith(SUFFIX)) {
                LOGGER.info("当前解析的博客：{}", file);
                String blogsContext = org.apache.commons.io.FileUtils.readFileToString(new File(file), "utf-8");

                String[] strs = blogsContext.split("----------");
                if (strs.length != 2) {
                    LOGGER.error("错误的博客文件，file={}", file);
                    continue;
                }
                String head = strs[0];
                int headS = head.indexOf("title:");
                LOGGER.info("title开始的位置:{}", headS);
                int dateS = head.indexOf("date:");
                LOGGER.info("date开始的位置:{}", dateS);
                if (headS == -1 || dateS == -1) {
                    LOGGER.error("错误的博客文件，headS==0 || dateS==0,file={}", file);
                    continue;
                }
                String title = head.substring(headS + 8, dateS - 2);
                int commentsS = head.indexOf("comments:");
                LOGGER.info("comments开始的位置:{}", commentsS);
                if (commentsS == -1) {
                    LOGGER.error("错误的博客文件，commentsS==0,file={}", file);
                    continue;
                }
                String date = head.substring(dateS + 6, commentsS - 7);

                int tagsS = head.indexOf("tags:");
                LOGGER.info("tags开始的位置:{}", tagsS);
                int keywordS = head.indexOf("keyword:");
                LOGGER.info("keyword开始的位置:{}", keywordS);
                if (tagsS == -1 || keywordS == -1) {
                    LOGGER.error("错误的博客文件，tagsS==0 || keywordS==0,file={}", file);
                    continue;
                }
                String tagStr = head.substring(tagsS + 7, keywordS - 2);
                tagStr = tagStr.replace(",", ";");

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date createTime = simpleDateFormat.parse(date);

                int summaryS = head.lastIndexOf("---") + 4;
                int summaryE = head.indexOf("<!-- more -->");
                if (summaryS == -1 || summaryE == -1) {
                    LOGGER.error("错误的博客文件，summaryS==0 || summaryE==0,file={}", file);
                    continue;
                }
                String summary = head.substring(summaryS, summaryE);
                if (summary.length() >= 512) {
                    summary = summary.substring(0, 510);
                }

                String body = strs[1];

                String content = body;
                if (content.indexOf("](http") != -1) {
                    LOGGER.error("错误的博客文件，content中包含图片,file={}", file);
                    continue;
                }
                content = content.replace("http://chenhaoxiang.cn", "http://huijava.com");
                content = content.replace("人生之旅_谙忆的博客", "谙忆的博客");

                BlogForm blogForm = new BlogForm();
                blogForm.setAuthor("");
                blogForm.setOriginalUrl("");
                blogForm.setTitle(title);
                blogForm.setSummary(summary);
                blogForm.setContent(content);
                blogForm.setTags(tagStr);
                blogForm.setCategoryId(3);

                Users users = usersService.selectUsersByUserName("admin");
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
                blogs.setBlogToken(StringUtils.getBlogToken());
                blogs.setPublishTime(createTime);
                blogs.setCreateTime(createTime);
                blogs.setUpdateTime(createTime);

                //原创文章
                blogs.setOriginal(new Byte(BlogOriginalEnum.ORIGINAL.getCode().toString()));
                blogs.setAuthor(users.getNikeName());
                if (StringUtils.isEmpty(users.getNikeName())) {
                    blogs.setAuthor(users.getUsername());
                }
                blogs.setOriginalUrl(BlogsConstans.BLOGS_ORIGINAL_URL + blogs.getBlogToken());

                blogs.setCreateId(users.getId());
                blogs.setUpdateId(users.getId());
                try {
                    blogsService.insertBlog(blogs, tagsList, oldTagsList, (byte) StatusEnum.ACTIVE.getCode());
                } catch (DataIntegrityViolationException e) {
                    LOGGER.error("插入博客失败，blogs={}", blogs);
                    continue;
                }
                LOGGER.info("插入博客成功，blogs.id={}", blogs.getId());
                FileUtils.deleteFile(new File(file));
            }
        }

    }


}