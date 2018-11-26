package com.huijava.superiorjavablogs.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "blogs_tags_r")
public class BlogsTagsR implements Serializable {

    private static final long serialVersionUID = -8648928356472087829L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 博客id
     */
    @Column(name = "blogs_id")
    private Integer blogsId;

    /**
     * 标签id
     */
    @Column(name = "tags_id")
    private Integer tagsId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取博客id
     *
     * @return blogs_id - 博客id
     */
    public Integer getBlogsId() {
        return blogsId;
    }

    /**
     * 设置博客id
     *
     * @param blogsId 博客id
     */
    public void setBlogsId(Integer blogsId) {
        this.blogsId = blogsId;
    }

    /**
     * 获取标签id
     *
     * @return tags_id - 标签id
     */
    public Integer getTagsId() {
        return tagsId;
    }

    /**
     * 设置标签id
     *
     * @param tagsId 标签id
     */
    public void setTagsId(Integer tagsId) {
        this.tagsId = tagsId;
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
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}