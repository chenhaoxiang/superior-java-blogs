/**
 * huijava.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.huijava.superiorjavablogs.dto;

import com.huijava.superiorjavablogs.common.base.BaseEntity;
import com.huijava.superiorjavablogs.entity.Category;
import com.huijava.superiorjavablogs.entity.Tags;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

/**
 * 博客详情需要的博客数据
 *
 * @author chenhx
 * @version BlogsDTO.java, v 0.1 2018-09-12 下午 8:54
 */
@Data
@ToString(callSuper = true)
public class BlogsDTO extends BaseEntity {
    /**
     * 博客包含标签
     */
    List<Tags> tagsList;
    /**
     * 博客的分类
     */
    private Category category;
    /**
     * 发表者
     */
    private String nikeName;
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 博客的索引- 创建规则就是博客发布的时间，例如201807201439568+15位随机数
     */
    @Column(name = "blog_token")
    private String blogToken;

    /**
     * 作者
     */
    private String author;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 原文链接
     */
    @Column(name = "original_url")
    private String originalUrl;

    /**
     * 原文发表时间
     */
    @Column(name = "publish_time")
    private Date publishTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 状态 0-正常 1-删除 2-待审核
     */
    private Byte status;

    /**
     * 被顶数
     */
    @Column(name = "top_count")
    private Integer topCount;

    /**
     * 浏览数
     */
    @Column(name = "view_count")
    private Integer viewCount;

    /**
     * 评论数
     */
    @Column(name = "comment_count")
    private Integer commentCount;

    /**
     * 类别id
     */
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * 创建者 - 也就是作者
     */
    @Column(name = "create_id")
    private Integer createId;

    /**
     * 修改者 - 不一定是作者，可能是管理员
     */
    @Column(name = "update_id")
    private Integer updateId;

    /**
     * 当前的版本
     */
    private Integer version;

    private String ext1;

    private String ext2;

    private String ext3;

    /**
     * 被收藏数
     */
    @Column(name = "collect_count")
    private Integer collectCount;

    /**
     * 原创标志 0-转载 1-原创 2-翻译
     */
    private Byte original;

    /**
     * 文章内容
     */
    private String content;

}