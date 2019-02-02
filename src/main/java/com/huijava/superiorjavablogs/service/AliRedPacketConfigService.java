package com.huijava.superiorjavablogs.service;

import com.huijava.superiorjavablogs.common.base.Service;
import com.huijava.superiorjavablogs.entity.AliRedPacketConfig;


/**
 * @author chenhaoxiang
 * @date 2019-02-01 01:41:24
 */
public interface AliRedPacketConfigService extends Service<AliRedPacketConfig> {

    Integer getMaxId(Integer id, Integer maxId);

    Integer getMinId(Integer id, Integer minId);

    /**
     * 修改领取红包记录
     *
     * @param redId
     * @param status
     * @return
     */
    Integer updateStatusByIdAndVersion(Integer redId, Integer status, Integer version);
}
