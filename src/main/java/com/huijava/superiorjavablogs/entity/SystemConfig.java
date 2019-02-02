package com.huijava.superiorjavablogs.entity;

import lombok.ToString;

import javax.persistence.*;

@Table(name = "system_config")
@ToString
public class SystemConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * key名称
     */
    @Column(name = "key_name")
    private String keyName;

    /**
     * 描述
     */
    @Column(name = "`describe`")
    private String describe;

    /**
     * int值
     */
    @Column(name = "int_value")
    private Integer intValue;

    /**
     * string值
     */
    @Column(name = "string_value")
    private String stringValue;

    /**
     * 状态，0-正常，1-禁用
     */
    @Column(name = "`status`")
    private Byte status;

    /**
     * 配置开始时间
     */
    @Column(name = "start_time")
    private Long startTime;

    /**
     * 配置结束时间
     */
    @Column(name = "end_time")
    private Long endTime;

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
     * 获取key名称
     *
     * @return key_name - key名称
     */
    public String getKeyName() {
        return keyName;
    }

    /**
     * 设置key名称
     *
     * @param keyName key名称
     */
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    /**
     * 获取描述
     *
     * @return describe - 描述
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * 设置描述
     *
     * @param describe 描述
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * 获取int值
     *
     * @return int_value - int值
     */
    public Integer getIntValue() {
        return intValue;
    }

    /**
     * 设置int值
     *
     * @param intValue int值
     */
    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    /**
     * 获取string值
     *
     * @return string_value - string值
     */
    public String getStringValue() {
        return stringValue;
    }

    /**
     * 设置string值
     *
     * @param stringValue string值
     */
    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    /**
     * 获取状态，0-正常，1-禁用
     *
     * @return status - 状态，0-正常，1-禁用
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态，0-正常，1-禁用
     *
     * @param status 状态，0-正常，1-禁用
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取配置开始时间
     *
     * @return start_time - 配置开始时间
     */
    public Long getStartTime() {
        return startTime;
    }

    /**
     * 设置配置开始时间
     *
     * @param startTime 配置开始时间
     */
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取配置结束时间
     *
     * @return end_time - 配置结束时间
     */
    public Long getEndTime() {
        return endTime;
    }

    /**
     * 设置配置结束时间
     *
     * @param endTime 配置结束时间
     */
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}