package com.huijava.superiorjavablogs.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "red_packet_details")
public class RedPacketDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * wx_users的id
     */
    @Column(name = "wx_users_id")
    private Integer wxUsersId;

    @Column(name = "ali_red_packet_config_id")
    private Integer aliRedPacketConfigId;

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
     * 时间戳，毫秒
     */
    @Column(name = "get_time")
    private Long getTime;

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
     * 获取wx_users的id
     *
     * @return wx_users_id - wx_users的id
     */
    public Integer getWxUsersId() {
        return wxUsersId;
    }

    /**
     * 设置wx_users的id
     *
     * @param wxUsersId wx_users的id
     */
    public void setWxUsersId(Integer wxUsersId) {
        this.wxUsersId = wxUsersId;
    }

    /**
     * @return ali_red_packet_config_id
     */
    public Integer getAliRedPacketConfigId() {
        return aliRedPacketConfigId;
    }

    /**
     * @param aliRedPacketConfigId
     */
    public void setAliRedPacketConfigId(Integer aliRedPacketConfigId) {
        this.aliRedPacketConfigId = aliRedPacketConfigId;
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
     * 获取时间戳，毫秒
     *
     * @return get_time - 时间戳，毫秒
     */
    public Long getGetTime() {
        return getTime;
    }

    /**
     * 设置时间戳，毫秒
     *
     * @param getTime 时间戳，毫秒
     */
    public void setGetTime(Long getTime) {
        this.getTime = getTime;
    }
}