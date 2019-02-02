package com.huijava.superiorjavablogs.service;

import com.huijava.superiorjavablogs.common.base.Service;
import com.huijava.superiorjavablogs.entity.SystemConfig;


/**
 * @author chenhaoxiang
 * @date 2019-02-02 12:14:06
 */
public interface SystemConfigService extends Service<SystemConfig> {
    SystemConfig getByKeyName(String keyName);
}
