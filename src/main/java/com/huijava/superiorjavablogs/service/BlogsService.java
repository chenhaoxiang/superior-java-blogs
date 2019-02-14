package com.huijava.superiorjavablogs.service;

import com.huijava.superiorjavablogs.common.base.Service;
import com.huijava.superiorjavablogs.common.enums.BlogFieldEnum;
import com.huijava.superiorjavablogs.dto.BlogsDTO;
import com.huijava.superiorjavablogs.entity.Blogs;
import com.huijava.superiorjavablogs.entity.Tags;

import java.util.List;


/**
 * @author chenhaoxiang
 * @date 2018-09-12 18:23:40
 */
public interface BlogsService extends Service<Blogs> {

    /**
     * 通过标签名查询标签
     *
     * @return
     */
    Tags selectTagsExtByTagsName(String tagsName);

    /**
     * 插入博客
     *
     * @param blogs
     * @param tagsList    需要新插入的标签
     * @param oldTagsList 已经存在的标签
     * @return
     */
    void insertBlog(Blogs blogs, List<Tags> tagsList, List<Tags> oldTagsList, byte status);

    /**
     * 通过blogToken获取具体的博客
     *
     * @param blogToken
     * @return
     */
    Blogs getBlogsByBlogToken(String blogToken);


    /**
     * 通过blogToken获取具体的博客
     * 显示博客的详情需要
     *
     * @param blogToken
     * @return
     */
    BlogsDTO getBlogsDTOByBlogToken(String blogToken);

    /**
     * 通过日期和数字获取 days天内number篇blogDataEnum排行最多的博客
     * （有浏览量，评论数，被顶数，收藏数等）
     *
     * @param days
     * @param number
     * @param blogFieldEnum
     * @return
     */
    List<Blogs> selectBlogsByDaysAndNumber(Integer days, Integer number, BlogFieldEnum blogFieldEnum);

    /**
     * 获取最新的博客number篇
     *
     * @return
     */
    List<BlogsDTO> selectNewBlogsDTOByNumber(Integer number, Integer status);

    /**
     * 获取最新的博客number篇
     *
     * @param number
     * @param status
     * @return
     */
    List<Blogs> selectNewBlogsByNumber(Integer number, Integer status);

    /**
     * 通过关键字模糊匹配
     * 暂时只匹配标题！
     *
     * @return
     */
    List<Blogs> selectBlogsByTitleKeyword(String keyWord);

    List<BlogsDTO> selectBlogsDTOByDaysAndNumber(int day, int number, BlogFieldEnum viewCount);

    /**
     * 增加博客访问量
     *
     * @return
     */
    void addBlogsViewsOne(String blogsToken);

    /**
     * 查询所有，根据id倒叙
     * 排除内容和摘要
     *
     * @return
     */
    List<Blogs> selectAllDescIdExContentAndSummary();
}
