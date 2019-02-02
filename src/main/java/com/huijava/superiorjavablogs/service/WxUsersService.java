package com.huijava.superiorjavablogs.service;

import com.huijava.superiorjavablogs.common.base.Service;
import com.huijava.superiorjavablogs.dto.WxUsersDTO;
import com.huijava.superiorjavablogs.entity.RedPacket;
import com.huijava.superiorjavablogs.entity.WxUsers;

import java.util.List;


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

    int insertSelective(WxUsers wxUsers);

    int updateByPrimaryKeySelective(WxUsers wxUsers);

    /**
     * @return
     */
    List<WxUsersDTO> findWxUsersDTOByPid(Integer pid);

    int bindingWxUsersPidAndAddTimes(WxUsers wxUsers2, RedPacket redPacket);
}
