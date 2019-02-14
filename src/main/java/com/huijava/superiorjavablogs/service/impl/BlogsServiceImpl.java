package com.huijava.superiorjavablogs.service.impl;

import com.google.common.collect.Lists;
import com.huijava.superiorjavablogs.common.base.AbstractService;
import com.huijava.superiorjavablogs.common.constant.BlogsConstans;
import com.huijava.superiorjavablogs.common.enums.BlogFieldEnum;
import com.huijava.superiorjavablogs.common.enums.StatusEnum;
import com.huijava.superiorjavablogs.dto.BlogsDTO;
import com.huijava.superiorjavablogs.entity.Blogs;
import com.huijava.superiorjavablogs.entity.BlogsTagsR;
import com.huijava.superiorjavablogs.entity.Tags;
import com.huijava.superiorjavablogs.mapper.BlogsMapper;
import com.huijava.superiorjavablogs.mapper.BlogsMapperExt;
import com.huijava.superiorjavablogs.mapper.BlogsTagsRMapper;
import com.huijava.superiorjavablogs.mapper.TagsMapper;
import com.huijava.superiorjavablogs.mapper.TagsMapperExt;
import com.huijava.superiorjavablogs.service.BlogsService;
import com.huijava.superiorjavablogs.util.DateUtils;
import com.huijava.superiorjavablogs.util.DefaultBeanUtils;
import com.huijava.superiorjavablogs.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author chenhaoxiang
 * @date 2018-09-12 18:23:40
 */
@Service
public class BlogsServiceImpl extends AbstractService<Blogs> implements BlogsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlogsServiceImpl.class);
    @Autowired
    private BlogsMapperExt blogsMapperExt;
    @Autowired
    private BlogsMapper blogsMapper;
    @Autowired
    private TagsMapperExt tagsMapperExt;
    @Autowired
    private TagsMapper tagsMapper;
    @Autowired
    private BlogsTagsRMapper blogsTagsRMapper;

    @Async
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addBlogsViewsOne(String blogsToken) {
        LOGGER.info("增加博客访问量,blogsToken={}", blogsToken);
        blogsMapperExt.addBlogsViewsOne(blogsToken);
    }

    @Override
    public BlogsDTO getBlogsDTOByBlogToken(String blogToken) {
        return blogsMapperExt.getBlogsDTOByBlogToken(blogToken);
    }

    @Override
    public Tags selectTagsExtByTagsName(String tagsName) {
        return tagsMapperExt.selectTagsByName(tagsName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBlog(Blogs newBlogs, List<Tags> tagsList, List<Tags> oldTagsList, byte status) {
        if (StringUtils.isBlank(newBlogs.getSummary())) {
            if (newBlogs.getContent().length() > BlogsConstans.MAX_SUMMARY_LENGTH) {
                newBlogs.setSummary(newBlogs.getContent().substring(0, BlogsConstans.MAX_SUMMARY_LENGTH) + "...");
            } else {
                newBlogs.setSummary(newBlogs.getContent());
            }
        }
        newBlogs.setStatus(status);
        Integer row = blogsMapper.insertSelective(newBlogs);
        LOGGER.debug("插入博客后返回的值：row={},blog.id={}", row, newBlogs.getId());
        if (!row.equals(1)) {
            throw new RuntimeException("插入博客失败");
        }
        if (tagsList.size() > 0) {
            tagsMapper.insertList(tagsList);
        }
        LOGGER.debug("插入的tagsList={}", tagsList);
        List<BlogsTagsR> rBlogsTagsList = Lists.newArrayList();
        addTagsListToRBlogsTags(tagsList, newBlogs.getId(), rBlogsTagsList);
        addTagsListToRBlogsTags(oldTagsList, newBlogs.getId(), rBlogsTagsList);
        if (rBlogsTagsList.size() > 0) {
            blogsTagsRMapper.insertList(rBlogsTagsList);
        }
    }

    private void addTagsListToRBlogsTags(List<Tags> oldTagsList, Integer blogId, List<BlogsTagsR> rBlogsTagsList) {
        for (Tags tags : oldTagsList) {
            LOGGER.debug("返回的tags={}", tags);
            if (tags.getId() != null) {
                BlogsTagsR rBlogsTags = new BlogsTagsR();
                rBlogsTags.setTagsId(tags.getId());
                rBlogsTags.setBlogsId(blogId);
                DefaultBeanUtils.defaultBlogsTagsR(rBlogsTags);
                rBlogsTagsList.add(rBlogsTags);
            }
        }
    }

    @Override
    public Blogs getBlogsByBlogToken(String blogToken) {
        return blogsMapperExt.selectByBlogToken(blogToken);
    }

    @Override
    public List<Blogs> selectBlogsByDaysAndNumber(Integer days, Integer number, BlogFieldEnum blogFieldEnum) {
        //获取今天以前days天的日期
        String day = DateUtils.getDateString(-1 * days);
        return blogsMapperExt.selectBlogsByDaysAndNumber(day, number, blogFieldEnum.getFieldName());
    }


    @Override
    public List<BlogsDTO> selectBlogsDTOByDaysAndNumber(int days, int number, BlogFieldEnum viewCount) {
        //获取今天以前days天的日期
        String day = DateUtils.getDateString(-1 * days);
        return blogsMapperExt.selectBlogsDTOByDaysAndNumber(day, number, viewCount, StatusEnum.ACTIVE.getCode());
    }

    @Override
    public List<BlogsDTO> selectNewBlogsDTOByNumber(Integer number, Integer status) {
        return blogsMapperExt.selectNewBlogsDTOByNumber(number, new Byte(status.toString()));
    }

    @Override
    public List<Blogs> selectNewBlogsByNumber(Integer number, Integer status) {
        return blogsMapperExt.selectNewBlogsByNumber(number, new Byte(status.toString()));
    }

    @Override
    public List<Blogs> selectBlogsByTitleKeyword(String keyWord) {
        return blogsMapperExt.selectBlogsByTitleKeyword(keyWord.trim());
    }

    @Override
    public List<Blogs> selectAllDescIdExContentAndSummary() {
        Example example = new Example(Blogs.class);
        //注意用的是类中的属性，不是数据库中的属性
        example.orderBy("id").desc();
        example.excludeProperties("content", "summary");
        return blogsMapper.selectByExample(example);
    }
}
