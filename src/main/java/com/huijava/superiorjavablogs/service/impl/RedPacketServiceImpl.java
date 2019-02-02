package com.huijava.superiorjavablogs.service.impl;

import com.huijava.superiorjavablogs.common.base.AbstractService;
import com.huijava.superiorjavablogs.common.exception.ServiceException;
import com.huijava.superiorjavablogs.dto.RedPacketDTO;
import com.huijava.superiorjavablogs.entity.*;
import com.huijava.superiorjavablogs.mapper.RedPacketDetailsMapper;
import com.huijava.superiorjavablogs.mapper.RedPacketMapper;
import com.huijava.superiorjavablogs.mapper.RedPacketMapperExt;
import com.huijava.superiorjavablogs.service.AliRedPacketConfigService;
import com.huijava.superiorjavablogs.service.RedPacketService;
import com.huijava.superiorjavablogs.service.SystemConfigService;
import com.huijava.superiorjavablogs.util.DateUtils;
import com.huijava.superiorjavablogs.util.pass.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author chenhaoxiang
 * @date 2019-02-01 01:41:24
 */
@Service
@Slf4j
public class RedPacketServiceImpl extends AbstractService<RedPacket> implements RedPacketService {
    @Autowired
    private RedPacketMapper redPacketMapper;
    @Autowired
    private RedPacketMapperExt redPacketMapperExt;

    @Autowired
    private SystemConfigService systemConfigService;

    @Autowired
    private AliRedPacketConfigService aliRedPacketConfigService;

    @Autowired
    private DataSourceTransactionManager transactionManager;

    @Autowired
    private RedPacketDetailsMapper redPacketDetailsMapper;

    @Override
    public int insertSelective(RedPacket redPacket) {
        return redPacketMapper.insertSelective(redPacket);
    }

    @Override
    public RedPacket getByInvitationCode(String invitationCode) {
        Example example = new Example(RedPacket.class);
        example.createCriteria().andCondition("invitation_code = ", invitationCode);
        return redPacketMapper.selectOneByExample(example);
    }

    @Override
    public RedPacket getByWxUsersId(Integer wxUsersId) {
        Example example = new Example(RedPacket.class);
        example.createCriteria().andCondition("wx_users_id = ", wxUsersId);
        return redPacketMapper.selectOneByExample(example);
    }

    @Override
    public List<RedPacketDTO> findRedPacketDTOList() {
        return redPacketMapperExt.findRedPacketDTOList();
    }

    @Override
    public String getRedPacket(RedPacket redPacket, WxUsers wxUsers) {
        log.info("领取红包口令，redPacket={},wxUsers={}", redPacket, wxUsers);
        //领取红包时间点

        //获取红包配置的key_name
        SystemConfig systemConfigMin = systemConfigService.getByKeyName("ali-red-packet-min-id");
        log.info("领取红包口令，获取的最小id配置，systemConfigMin={}", systemConfigMin);
        if (systemConfigMin == null) {
            return "未到红包领取时间";
        }
        SystemConfig systemConfigMax = systemConfigService.getByKeyName("ali-red-packet-max-id");
        log.info("领取红包口令，获取的最大id配置，systemConfigMax={}", systemConfigMax);
        if (systemConfigMax == null) {
            return "未到红包领取时间";
        }
        Integer minId = systemConfigMin.getIntValue();
        Integer maxId = systemConfigMax.getIntValue();

        //随机获取红包
        String openid = wxUsers.getOpenid();
        String md5Openid = MD5Utils.md5Encode(openid);
        log.info("领取口令红包，openid={},md5Openid={}", openid, md5Openid);
        Integer hashCode = md5Openid.hashCode();
        Integer numId = hashCode % maxId + minId;
        log.info("领取口令红包,hashCode={},numId:{},openid={}", hashCode, numId, openid);


        //获取最大id的红包
        Integer redId = aliRedPacketConfigService.getMaxId(numId, maxId);
        log.info("1领取口令红包,redId:{},openid={}", redId, openid);
        if (redId == null || redId.equals(0)) {
            //获取最小id的红包
            redId = aliRedPacketConfigService.getMinId(numId, minId);
            log.info("2领取口令红包,redId:{},openid={}", redId, openid);
            if (redId == null || redId.equals(0)) {
                return "红包已全部被领取";
            }
        }
        AliRedPacketConfig aliRedPacketConfig = aliRedPacketConfigService.selectById(redId);
        log.info("3领取口令红包,获取的红包信息:{},openid={}", aliRedPacketConfig, openid);
        if (aliRedPacketConfig == null || !(aliRedPacketConfig.getStatus().intValue() == 0)) {
            return "手慢了,红包刚被领取,请重试";
        }

        //开启事务
        TransactionStatus transaction = null;
        //获取红包
        try {
            transaction = transactionManager.getTransaction(new DefaultTransactionDefinition());
            //修改红包为已领取
            Integer rows = aliRedPacketConfigService.updateStatusByIdAndVersion(redId, 1, aliRedPacketConfig.getVersion());
            log.info("领取口令红包,修改红包状态为已领取，影响行数={},openid={}", rows, openid);
            if (rows != 1) {
                log.warn("领取口令红包,修改红包状态为已领取,红包被领取或者修改数据库失败，影响行数={},openid={}", rows, openid);
                throw new ServiceException("红包被领取或者修改数据库失败");
            }
            //累加红包金额以及次数
            rows = redPacketMapperExt.updateTimesAndSumMoneyByWxUsersId(wxUsers.getId(), aliRedPacketConfig.getMoney());
            log.info("领取口令红包,修改领取总金额和领取次数，影响行数={},openid={}", rows, openid);
            if (rows != 1) {
                log.warn("领取口令红包,领取次数不够,修改领取总金额和领取次数，影响行数={},openid={}", rows, openid);
                throw new ServiceException("领取次数不够");
            }
            //增加红包明细
            RedPacketDetails redPacketDetails = new RedPacketDetails();
            redPacketDetails.setAliRedPacketConfigId(aliRedPacketConfig.getId());
            redPacketDetails.setGetTime(DateUtils.getLongDateTimeMS());
            redPacketDetails.setWxUsersId(wxUsers.getId());
            rows = redPacketDetailsMapper.insertSelective(redPacketDetails);
            log.info("领取口令红包,插入红包记录明细，影响行数={},openid={},redPacketDetails={}", rows, openid, redPacketDetails);
            if (rows != 1) {
                log.warn("领取口令红包,插入数据失败,插入红包记录明细，影响行数={},openid={}", rows, openid);
                throw new ServiceException("插入数据失败,红包已被领取");
            }

            transactionManager.commit(transaction);
            log.info("领取口令红包成功，users={},红包信息:{}", wxUsers, aliRedPacketConfig);
            return "支付宝红包口令:" + aliRedPacketConfig.getPassword();
        } catch (Exception e) {
            //回滚
            if (transaction != null) {
                transactionManager.rollback(transaction);
            }
            log.warn("领取口令红包出现异常,事物回滚,wxUsers={}", wxUsers);
            log.error("领取口令红包出现异常，事物回滚", e);
        }
        return "领取失败";
    }
}
