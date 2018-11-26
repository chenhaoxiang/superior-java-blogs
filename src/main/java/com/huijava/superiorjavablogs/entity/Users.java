package com.huijava.superiorjavablogs.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public class Users implements Serializable {

    private static final long serialVersionUID = -8314352523375300461L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 加密盐
     */
    private String salt;

    /**
     * 状态 0-正常 1-逻辑删除
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
     * 角色  与角色表关联的id 1-管理员 2-普通用户
     */
    @Column(name = "roles_id")
    private Integer rolesId;

    /**
     * 用户的主页url
     */
    @Column(name = "home_url")
    private String homeUrl;

    /**
     * 个性签名
     */
    private String sign;

    @Column(name = "update_id")
    private Integer updateId;

    @Column(name = "create_id")
    private Integer createId;

    /**
     * 昵称
     */
    @Column(name = "nike_name")
    private String nikeName;

    /**
     * 手机号码
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;

    /**
     * 头像地址
     */
    @Column(name = "head_image")
    private String headImage;

    /**
     * 对于该用户的评论
     */
    private String comment;

    private String ext1;

    private String ext2;

    private String ext3;

    /**
     * 贡献点
     */
    @Column(name = "contribute_count")
    private Long contributeCount;

    /**
     * 贡献权重 - 贡献度 - 相当于累计的贡献点
     */
    @Column(name = "contribute_weight")
    private Long contributeWeight;

    /**
     * 粉丝数
     */
    @Column(name = "followers_count")
    private Integer followersCount;

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
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取电子邮箱
     *
     * @return email - 电子邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置电子邮箱
     *
     * @param email 电子邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取加密盐
     *
     * @return salt - 加密盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置加密盐
     *
     * @param salt 加密盐
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取状态 0-正常 1-逻辑删除
     *
     * @return status - 状态 0-正常 1-逻辑删除
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态 0-正常 1-逻辑删除
     *
     * @param status 状态 0-正常 1-逻辑删除
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
     * 获取角色  与角色表关联的id 1-管理员 2-普通用户
     *
     * @return roles_id - 角色  与角色表关联的id 1-管理员 2-普通用户
     */
    public Integer getRolesId() {
        return rolesId;
    }

    /**
     * 设置角色  与角色表关联的id 1-管理员 2-普通用户
     *
     * @param rolesId 角色  与角色表关联的id 1-管理员 2-普通用户
     */
    public void setRolesId(Integer rolesId) {
        this.rolesId = rolesId;
    }

    /**
     * 获取用户的主页url
     *
     * @return home_url - 用户的主页url
     */
    public String getHomeUrl() {
        return homeUrl;
    }

    /**
     * 设置用户的主页url
     *
     * @param homeUrl 用户的主页url
     */
    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }

    /**
     * 获取个性签名
     *
     * @return sign - 个性签名
     */
    public String getSign() {
        return sign;
    }

    /**
     * 设置个性签名
     *
     * @param sign 个性签名
     */
    public void setSign(String sign) {
        this.sign = sign;
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
     * 获取昵称
     *
     * @return nike_name - 昵称
     */
    public String getNikeName() {
        return nikeName;
    }

    /**
     * 设置昵称
     *
     * @param nikeName 昵称
     */
    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    /**
     * 获取手机号码
     *
     * @return mobile_phone - 手机号码
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设置手机号码
     *
     * @param mobilePhone 手机号码
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * 获取头像地址
     *
     * @return head_image - 头像地址
     */
    public String getHeadImage() {
        return headImage;
    }

    /**
     * 设置头像地址
     *
     * @param headImage 头像地址
     */
    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    /**
     * 获取对于该用户的评论
     *
     * @return comment - 对于该用户的评论
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置对于该用户的评论
     *
     * @param comment 对于该用户的评论
     */
    public void setComment(String comment) {
        this.comment = comment;
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
     * 获取贡献点
     *
     * @return contribute_count - 贡献点
     */
    public Long getContributeCount() {
        return contributeCount;
    }

    /**
     * 设置贡献点
     *
     * @param contributeCount 贡献点
     */
    public void setContributeCount(Long contributeCount) {
        this.contributeCount = contributeCount;
    }

    /**
     * 获取贡献权重 - 贡献度 - 相当于累计的贡献点
     *
     * @return contribute_weight - 贡献权重 - 贡献度 - 相当于累计的贡献点
     */
    public Long getContributeWeight() {
        return contributeWeight;
    }

    /**
     * 设置贡献权重 - 贡献度 - 相当于累计的贡献点
     *
     * @param contributeWeight 贡献权重 - 贡献度 - 相当于累计的贡献点
     */
    public void setContributeWeight(Long contributeWeight) {
        this.contributeWeight = contributeWeight;
    }

    /**
     * 获取粉丝数
     *
     * @return followers_count - 粉丝数
     */
    public Integer getFollowersCount() {
        return followersCount;
    }

    /**
     * 设置粉丝数
     *
     * @param followersCount 粉丝数
     */
    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }
}