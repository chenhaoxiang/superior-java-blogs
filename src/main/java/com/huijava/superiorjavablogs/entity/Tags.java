package com.huijava.superiorjavablogs.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public class Tags implements Serializable {

    private static final long serialVersionUID = 7406156893751788468L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标签名 - 英文
     */
    private String name;

    /**
     * 说明
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
     * 标签状态 0-正常 1-逻辑删除
     */
    private Byte status;

    @Column(name = "create_id")
    private Integer createId;

    @Column(name = "update_id")
    private Integer updateId;

    /**
     * 标签的颜色 - bootstrap中的 label-*** 字符串
     */
    @Column(name = "color_name")
    private String colorName;

    /**
     * 排序权重
     */
    private Integer sort;

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
     * 获取标签名 - 英文
     *
     * @return name - 标签名 - 英文
     */
    public String getName() {
        return name;
    }

    /**
     * 设置标签名 - 英文
     *
     * @param name 标签名 - 英文
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取说明
     *
     * @return comment - 说明
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置说明
     *
     * @param comment 说明
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
     * 获取标签状态 0-正常 1-逻辑删除
     *
     * @return status - 标签状态 0-正常 1-逻辑删除
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置标签状态 0-正常 1-逻辑删除
     *
     * @param status 标签状态 0-正常 1-逻辑删除
     */
    public void setStatus(Byte status) {
        this.status = status;
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
     * 获取标签的颜色 - bootstrap中的 label-*** 字符串
     *
     * @return color_name - 标签的颜色 - bootstrap中的 label-*** 字符串
     */
    public String getColorName() {
        return colorName;
    }

    /**
     * 设置标签的颜色 - bootstrap中的 label-*** 字符串
     *
     * @param colorName 标签的颜色 - bootstrap中的 label-*** 字符串
     */
    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    /**
     * 获取排序权重
     *
     * @return sort - 排序权重
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序权重
     *
     * @param sort 排序权重
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}