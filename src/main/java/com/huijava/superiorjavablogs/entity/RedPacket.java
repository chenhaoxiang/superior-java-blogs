package com.huijava.superiorjavablogs.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "red_packet")
public class RedPacket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * wx_users表的id
     */
    @Column(name = "wx_users_id")
    private Integer wxUsersId;

    /**
     * 已经获取红包的次数
     */
    private Integer times;

    /**
     * 最大获取红包的次数
     */
    @Column(name = "max_times")
    private Integer maxTimes;

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
     * 邀请码
     */
    @Column(name = "invitation_code")
    private String invitationCode;

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
     * 获取wx_users表的id
     *
     * @return wx_users_id - wx_users表的id
     */
    public Integer getWxUsersId() {
        return wxUsersId;
    }

    /**
     * 设置wx_users表的id
     *
     * @param wxUsersId wx_users表的id
     */
    public void setWxUsersId(Integer wxUsersId) {
        this.wxUsersId = wxUsersId;
    }

    /**
     * 获取已经获取红包的次数
     *
     * @return times - 已经获取红包的次数
     */
    public Integer getTimes() {
        return times;
    }

    /**
     * 设置已经获取红包的次数
     *
     * @param times 已经获取红包的次数
     */
    public void setTimes(Integer times) {
        this.times = times;
    }

    /**
     * 获取最大获取红包的次数
     *
     * @return max_times - 最大获取红包的次数
     */
    public Integer getMaxTimes() {
        return maxTimes;
    }

    /**
     * 设置最大获取红包的次数
     *
     * @param maxTimes 最大获取红包的次数
     */
    public void setMaxTimes(Integer maxTimes) {
        this.maxTimes = maxTimes;
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
     * 获取邀请码
     *
     * @return invitation_code - 邀请码
     */
    public String getInvitationCode() {
        return invitationCode;
    }

    /**
     * 设置邀请码
     *
     * @param invitationCode 邀请码
     */
    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }
}