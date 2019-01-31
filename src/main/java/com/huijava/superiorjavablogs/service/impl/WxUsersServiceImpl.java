package com.huijava.superiorjavablogs.service.impl;

import com.huijava.superiorjavablogs.common.base.AbstractService;
import com.huijava.superiorjavablogs.entity.WxUsers;
import com.huijava.superiorjavablogs.mapper.WxUsersMapper;
import com.huijava.superiorjavablogs.service.WxUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @author chenhaoxiang
 * @date 2019-02-01 01:41:24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WxUsersServiceImpl extends AbstractService<WxUsers> implements WxUsersService {
    @Autowired
    private WxUsersMapper wxUsersMapper;

    public WxUsers getWxUsersByOpenId(String openid) {
        Example example = new Example(WxUsers.class);
        example.createCriteria().andCondition("openid = " + openid);
        return wxUsersMapper.selectOneByExample(example);
    }

}
