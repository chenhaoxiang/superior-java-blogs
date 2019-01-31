package com.huijava.superiorjavablogs.service;

import com.huijava.superiorjavablogs.common.base.Service;
import com.huijava.superiorjavablogs.entity.WxUsers;


/**
 * @author chenhaoxiang
 * @date 2019-02-01 01:41:24
 */
public interface WxUsersService extends Service<WxUsers> {
    /**
     * 通过openid获取wx_users
     *
     * @param openid
     * @return
     */
    WxUsers getWxUsersByOpenId(String openid);
}
