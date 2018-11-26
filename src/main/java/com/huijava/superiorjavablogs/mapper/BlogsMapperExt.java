package com.huijava.superiorjavablogs.mapper;

import com.huijava.superiorjavablogs.common.enums.BlogFieldEnum;
import com.huijava.superiorjavablogs.dto.BlogsDTO;
import com.huijava.superiorjavablogs.entity.Blogs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogsMapperExt {
    /**
     * TODO 关键字搜索，后期必须优化的
     *
     * @param keyWord
     * @return
     */
    List<Blogs> selectBlogsByTitleKeyword(@Param("keyWord") String keyWord);

    /**
     * 获取最新的博客number篇
     *
     * @param number
     * @return
     */
    List<BlogsDTO> selectNewBlogsDTOByNumber(@Param("number") Integer number, @Param("status") Byte status);

    /**
     * 查询最新的number篇博客
     *
     * @param number
     * @param status
     * @return
     */
    List<Blogs> selectNewBlogsByNumber(@Param("number") Integer number, @Param("status") Byte status);

    /**
     * 通过日期和数字获取 day天后 number篇blogDataEnum排行最多的博客
     * （有浏览量，评论数，被顶数，收藏数等）
     *
     * @param day
     * @param number
     * @param fieldName
     * @return
     */
    List<Blogs> selectBlogsByDaysAndNumber(@Param("day") String day
            , @Param("number") Integer number, @Param("fieldName") String fieldName);

    List<BlogsDTO> selectBlogsDTOByDaysAndNumber(@Param("day") String day
            , @Param("number") int number, @Param("fieldName") BlogFieldEnum viewCount, @Param("status") Integer status);

    /**
     * 通过blogsToken查询博客信息
     *
     * @param blogToken
     * @return
     */
    Blogs selectByBlogToken(@Param("blogToken") String blogToken);

    BlogsDTO getBlogsDTOByBlogToken(@Param("blogToken") String blogToken);

    /**
     * 增加博客访问量
     *
     * @param blogsToken
     * @return
     */
    Integer addBlogsViewsOne(@Param("blogToken") String blogsToken);
}