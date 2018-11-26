package com.huijava.superiorjavablogs.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public class Blogs implements Serializable {

    private static final long serialVersionUID = -8706960462099394558L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取博客的索引- 创建规则就是博客发布的时间，例如201807201439568+15位随机数
     *
     * @return blog_token - 博客的索引- 创建规则就是博客发布的时间，例如201807201439568+15位随机数
     */
    public String getBlogToken() {
        return blogToken;
    }

    /**
     * 设置博客的索引- 创建规则就是博客发布的时间，例如201807201439568+15位随机数
     *
     * @param blogToken 博客的索引- 创建规则就是博客发布的时间，例如201807201439568+15位随机数
     */
    public void setBlogToken(String blogToken) {
        this.blogToken = blogToken;
    }

    /**
     * 获取作者
     *
     * @return author - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取摘要
     *
     * @return summary - 摘要
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置摘要
     *
     * @param summary 摘要
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 获取原文链接
     *
     * @return original_url - 原文链接
     */
    public String getOriginalUrl() {
        return originalUrl;
    }

    /**
     * 设置原文链接
     *
     * @param originalUrl 原文链接
     */
    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    /**
     * 获取原文发表时间
     *
     * @return publish_time - 原文发表时间
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * 设置原文发表时间
     *
     * @param publishTime 原文发表时间
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取状态 0-正常 1-删除 2-待审核
     *
     * @return status - 状态 0-正常 1-删除 2-待审核
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态 0-正常 1-删除 2-待审核
     *
     * @param status 状态 0-正常 1-删除 2-待审核
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取被顶数
     *
     * @return top_count - 被顶数
     */
    public Integer getTopCount() {
        return topCount;
    }

    /**
     * 设置被顶数
     *
     * @param topCount 被顶数
     */
    public void setTopCount(Integer topCount) {
        this.topCount = topCount;
    }

    /**
     * 获取浏览数
     *
     * @return view_count - 浏览数
     */
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * 设置浏览数
     *
     * @param viewCount 浏览数
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * 获取评论数
     *
     * @return comment_count - 评论数
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * 设置评论数
     *
     * @param commentCount 评论数
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * 获取类别id
     *
     * @return category_id - 类别id
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置类别id
     *
     * @param categoryId 类别id
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取创建者 - 也就是作者
     *
     * @return create_id - 创建者 - 也就是作者
     */
    public Integer getCreateId() {
        return createId;
    }

    /**
     * 设置创建者 - 也就是作者
     *
     * @param createId 创建者 - 也就是作者
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
     * 获取修改者 - 不一定是作者，可能是管理员
     *
     * @return update_id - 修改者 - 不一定是作者，可能是管理员
     */
    public Integer getUpdateId() {
        return updateId;
    }

    /**
     * 设置修改者 - 不一定是作者，可能是管理员
     *
     * @param updateId 修改者 - 不一定是作者，可能是管理员
     */
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    /**
     * 获取当前的版本
     *
     * @return version - 当前的版本
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置当前的版本
     *
     * @param version 当前的版本
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * @return ext1
     */
    public String getExt1() {
        return ext1;
    }

    /**
     * @param ext1
     */
    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    /**
     * @return ext2
     */
    public String getExt2() {
        return ext2;
    }

    /**
     * @param ext2
     */
    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    /**
     * @return ext3
     */
    public String getExt3() {
        return ext3;
    }

    /**
     * @param ext3
     */
    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    /**
     * 获取被收藏数
     *
     * @return collect_count - 被收藏数
     */
    public Integer getCollectCount() {
        return collectCount;
    }

    /**
     * 设置被收藏数
     *
     * @param collectCount 被收藏数
     */
    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    /**
     * 获取原创标志 0-转载 1-原创 2-翻译
     *
     * @return original - 原创标志 0-转载 1-原创 2-翻译
     */
    public Byte getOriginal() {
        return original;
    }

    /**
     * 设置原创标志 0-转载 1-原创 2-翻译
     *
     * @param original 原创标志 0-转载 1-原创 2-翻译
     */
    public void setOriginal(Byte original) {
        this.original = original;
    }

    /**
     * 获取文章内容
     *
     * @return content - 文章内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置文章内容
     *
     * @param content 文章内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}