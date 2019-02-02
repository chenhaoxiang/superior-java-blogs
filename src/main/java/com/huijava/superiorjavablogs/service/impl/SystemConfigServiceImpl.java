package com.huijava.superiorjavablogs.service.impl;

import com.huijava.superiorjavablogs.common.base.AbstractService;
import com.huijava.superiorjavablogs.entity.SystemConfig;
import com.huijava.superiorjavablogs.mapper.SystemConfigMapper;
import com.huijava.superiorjavablogs.service.SystemConfigService;
import com.huijava.superiorjavablogs.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @author chenhaoxiang
 * @date 2019-02-02 12:14:06
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SystemConfigServiceImpl extends AbstractService<SystemConfig> implements SystemConfigService {
    @Autowired
    private SystemConfigMapper systemConfigMapper;

    @Override
    public SystemConfig getByKeyName(String keyName) {
        Example example = new Example(SystemConfig.class);
        long time = DateUtils.getLongDateTimeMS();
        example.selectProperties("id", "keyName", "describe", "intValue", "stringValue", "status", "startTime", "endTime")
                .createCriteria().andCondition("`key_name` = ", keyName)
                .andCondition("`status`=", 0)
                .andCondition("start_time < ", time)
                .andCondition("end_time > ", time);
        return systemConfigMapper.selectOneByExample(example);
    }

}
