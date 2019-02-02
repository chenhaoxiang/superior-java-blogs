package com.huijava.superiorjavablogs.entity;

import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Table(name = "wx_users")
@ToString
public class WxUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    private Byte subscribe;

    /**
     * 用户的标识，对当前公众号唯一
     */
    private String openid;

    /**
     * 用户的昵称
     */
    private String nickname;

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private Byte sex;

    /**
     * 用户所在城市
     */
    private String city;

    /**
     * 用户所在国家
     */
    private String country;

    /**
     * 用户状态 0-正常，1-禁用
     */
    private Byte status;

    /**
     * 用户所在省份
     */
    private String province;

    /**
     * 用户的语言，简体中文为zh_CN
     */
    private String language;

    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     */
    private String headimgurl;

    /**
     * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    @Column(name = "subscribe_time")
    private Long subscribeTime;

    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    private String unionid;

    /**
     * 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    private String remark;

    /**
     * 用户所在的分组ID（兼容旧的用户分组接口）
     */
    private Integer groupid;

    /**
     * 返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
     */
    @Column(name = "subscribe_scene")
    private String subscribeScene;

    /**
     * 父类id
     */
    private Integer pid;

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
     * 获取用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     *
     * @return subscribe - 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    public Byte getSubscribe() {
        return subscribe;
    }

    /**
     * 设置用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     *
     * @param subscribe 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    public void setSubscribe(Byte subscribe) {
        this.subscribe = subscribe;
    }

    /**
     * 获取用户的标识，对当前公众号唯一
     *
     * @return openid - 用户的标识，对当前公众号唯一
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置用户的标识，对当前公众号唯一
     *
     * @param openid 用户的标识，对当前公众号唯一
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取用户的昵称
     *
     * @return nickname - 用户的昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置用户的昵称
     *
     * @param nickname 用户的昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     *
     * @return sex - 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * 设置用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     *
     * @param sex 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * 获取用户所在城市
     *
     * @return city - 用户所在城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置用户所在城市
     *
     * @param city 用户所在城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取用户所在国家
     *
     * @return country - 用户所在国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置用户所在国家
     *
     * @param country 用户所在国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取用户状态 0-正常，1-禁用
     *
     * @return status - 用户状态 0-正常，1-禁用
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置用户状态 0-正常，1-禁用
     *
     * @param status 用户状态 0-正常，1-禁用
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取用户所在省份
     *
     * @return province - 用户所在省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置用户所在省份
     *
     * @param province 用户所在省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取用户的语言，简体中文为zh_CN
     *
     * @return language - 用户的语言，简体中文为zh_CN
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 设置用户的语言，简体中文为zh_CN
     *
     * @param language 用户的语言，简体中文为zh_CN
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 获取用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     *
     * @return headimgurl - 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     */
    public String getHeadimgurl() {
        return headimgurl;
    }

    /**
     * 设置用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     *
     * @param headimgurl 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     */
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    /**
     * 获取用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     *
     * @return subscribe_time - 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    public Long getSubscribeTime() {
        return subscribeTime;
    }

    /**
     * 设置用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     *
     * @param subscribeTime 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    public void setSubscribeTime(Long subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    /**
     * 获取只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     *
     * @return unionid - 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    public String getUnionid() {
        return unionid;
    }

    /**
     * 设置只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     *
     * @param unionid 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    /**
     * 获取公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     *
     * @return remark - 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     *
     * @param remark 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取用户所在的分组ID（兼容旧的用户分组接口）
     *
     * @return groupid - 用户所在的分组ID（兼容旧的用户分组接口）
     */
    public Integer getGroupid() {
        return groupid;
    }

    /**
     * 设置用户所在的分组ID（兼容旧的用户分组接口）
     *
     * @param groupid 用户所在的分组ID（兼容旧的用户分组接口）
     */
    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    /**
     * 获取返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
     *
     * @return subscribe_scene - 返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
     */
    public String getSubscribeScene() {
        return subscribeScene;
    }

    /**
     * 设置返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
     *
     * @param subscribeScene 返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
     */
    public void setSubscribeScene(String subscribeScene) {
        this.subscribeScene = subscribeScene;
    }

    /**
     * 获取父类id
     *
     * @return pid - 父类id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置父类id
     *
     * @param pid 父类id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
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
}