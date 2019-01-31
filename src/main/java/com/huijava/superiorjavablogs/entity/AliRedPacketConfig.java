package com.huijava.superiorjavablogs.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "ali_red_packet_config")
public class AliRedPacketConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 红包口令- 20个字符以内
     */
    private String password;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 1- 20%的次数可以参与该红包领取， 2-80%的可以参与该红包领取
     */
    private Byte type;

    /**
     * 0-未被领取，1-已被领取，2-废弃
     */
    private Byte status;

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
     * 版本号，乐观锁，控制并发
     */
    private Integer version;

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
     * 获取红包口令- 20个字符以内
     *
     * @return password - 红包口令- 20个字符以内
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置红包口令- 20个字符以内
     *
     * @param password 红包口令- 20个字符以内
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取金额
     *
     * @return money - 金额
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * 设置金额
     *
     * @param money 金额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 获取1- 20%的次数可以参与该红包领取， 2-80%的可以参与该红包领取
     *
     * @return type - 1- 20%的次数可以参与该红包领取， 2-80%的可以参与该红包领取
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置1- 20%的次数可以参与该红包领取， 2-80%的可以参与该红包领取
     *
     * @param type 1- 20%的次数可以参与该红包领取， 2-80%的可以参与该红包领取
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获取0-未被领取，1-已被领取，2-废弃
     *
     * @return status - 0-未被领取，1-已被领取，2-废弃
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置0-未被领取，1-已被领取，2-废弃
     *
     * @param status 0-未被领取，1-已被领取，2-废弃
     */
    public void setStatus(Byte status) {
        this.status = status;
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
     * 获取版本号，乐观锁，控制并发
     *
     * @return version - 版本号，乐观锁，控制并发
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置版本号，乐观锁，控制并发
     *
     * @param version 版本号，乐观锁，控制并发
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}