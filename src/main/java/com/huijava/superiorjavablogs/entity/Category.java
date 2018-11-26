package com.huijava.superiorjavablogs.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public class Category implements Serializable {

    private static final long serialVersionUID = -4600586702523383363L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 分类名
     */
    private String name;

    /**
     * 分类说明
     */
    private String comment;

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
     * 状态 0-正常 1-删除
     */
    private Byte status;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "create_id")
    private Integer createId;

    @Column(name = "update_id")
    private Integer updateId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 祖先分类级层关系。以逗号分隔
     */
    @Column(name = "parent_ids")
    private String parentIds;

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
     * 获取分类名
     *
     * @return name - 分类名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置分类名
     *
     * @param name 分类名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取分类说明
     *
     * @return comment - 分类说明
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置分类说明
     *
     * @param comment 分类说明
     */
    public void setComment(String comment) {
        this.comment = comment;
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
     * 获取状态 0-正常 1-删除
     *
     * @return status - 状态 0-正常 1-删除
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态 0-正常 1-删除
     *
     * @param status 状态 0-正常 1-删除
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * @return parent_id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return create_id
     */
    public Integer getCreateId() {
        return createId;
    }

    /**
     * @param createId
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
     * @return update_id
     */
    public Integer getUpdateId() {
        return updateId;
    }

    /**
     * @param updateId
     */
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取祖先分类级层关系。以逗号分隔
     *
     * @return parent_ids - 祖先分类级层关系。以逗号分隔
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * 设置祖先分类级层关系。以逗号分隔
     *
     * @param parentIds 祖先分类级层关系。以逗号分隔
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }
}