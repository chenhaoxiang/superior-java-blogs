package com.huijava.superiorjavablogs.service.impl;

import com.huijava.superiorjavablogs.common.base.AbstractService;
import com.huijava.superiorjavablogs.common.exception.ServiceException;
import com.huijava.superiorjavablogs.dto.WxUsersDTO;
import com.huijava.superiorjavablogs.entity.RedPacket;
import com.huijava.superiorjavablogs.entity.SystemConfig;
import com.huijava.superiorjavablogs.entity.WxUsers;
import com.huijava.superiorjavablogs.mapper.RedPacketMapper;
import com.huijava.superiorjavablogs.mapper.WxUsersMapper;
import com.huijava.superiorjavablogs.mapper.WxUsersMapperExt;
import com.huijava.superiorjavablogs.service.SystemConfigService;
import com.huijava.superiorjavablogs.service.WxUsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author chenhaoxiang
 * @date 2019-02-01 01:41:24
 */
@Service
@Slf4j
public class WxUsersServiceImpl extends AbstractService<WxUsers> implements WxUsersService {
    @Autowired
    private WxUsersMapper wxUsersMapper;
    @Autowired
    private WxUsersMapperExt wxUsersMapperExt;
    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private RedPacketMapper redPacketMapper;

    @Autowired
    private DataSourceTransactionManager transactionManager;
    @Override
    public WxUsers getWxUsersByOpenId(String openid) {
        Example example = new Example(WxUsers.class);
        example.createCriteria().andCondition("openid=", openid);
        return wxUsersMapper.selectOneByExample(example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(WxUsers wxUsers) {
        return wxUsersMapper.insertSelective(wxUsers);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(WxUsers wxUsers) {
        return wxUsersMapper.updateByPrimaryKeySelective(wxUsers);
    }

    @Override
    public List<WxUsersDTO> findWxUsersDTOByPid(Integer pid) {
        return wxUsersMapperExt.findWxUsersDTOByPid(pid);
    }

    @Override
    public int bindingWxUsersPidAndAddTimes(WxUsers wxUsers2, RedPacket redPacket) {
        //开启事务
        TransactionStatus transaction = null;
        //获取红包
        try {
            transaction = transactionManager.getTransaction(new DefaultTransactionDefinition());
            //修改pid
            WxUsers wxUsers = new WxUsers();
            wxUsers.setId(wxUsers2.getId());
            wxUsers.setPid(wxUsers2.getPid());

            Example wxExample = new Example(WxUsers.class);
            wxExample.createCriteria().andCondition("pid=0").
                    andCondition("id=", wxUsers.getId());
            int rows = wxUsersMapper.updateByExampleSelective(wxUsers, wxExample);

            if (rows != 1) {
                log.warn("修改邀请人数据，wxUsers2={},rows={}", wxUsers2, rows);
                throw new ServiceException("插入数据失败");
            }
            //增加次数
            //获取最大的次数
            SystemConfig systemConfig = systemConfigService.getByKeyName("binding-max-times");
            Integer maxTimes = 3;
            if (systemConfig != null) {
                maxTimes = systemConfig.getIntValue();
            }
            //判断最大次数
            if (redPacket.getMaxTimes() < maxTimes) {
                //先查询一次
                redPacket = redPacketMapper.selectByPrimaryKey(redPacket.getId());
                //增加次数
                Example example = new Example(RedPacket.class);
                example.createCriteria().andCondition("max_times=max_times")
                        .andCondition("wx_users_id=", wxUsers.getPid()).
                        andCondition("max_times <", maxTimes);
                RedPacket redPacket1 = new RedPacket();
                redPacket1.setId(redPacket.getId());
                redPacket1.setMaxTimes(redPacket.getMaxTimes() + 1);

                rows = redPacketMapper.updateByExampleSelective(redPacket1, example);
                if (rows != 1) {
                    //TODO 修改失败 - 这里有可能造成绑定了但是没有修改数据的情况，容错，不处理.所以该方法目前的事务也没有啥用处
                    log.error("绑定时增加次数失败，redPacket={},systemConfig={},maxTimes={},wxUsers={}", redPacket, systemConfig, maxTimes, wxUsers);
                }
            }
            transactionManager.commit(transaction);
        } catch (Exception e) {

            //回滚
            if (transaction != null) {
                transactionManager.rollback(transaction);
            }
            return 0;
        }
        return 1;
    }
}
