package com.huijava.superiorjavablogs.service;

import com.huijava.superiorjavablogs.common.base.Service;
import com.huijava.superiorjavablogs.dto.RedPacketDetailsDTO;
import com.huijava.superiorjavablogs.entity.RedPacketDetails;

import java.util.List;


/**
 * @author chenhaoxiang
 * @date 2019-02-01 01:41:24
 */
public interface RedPacketDetailsService extends Service<RedPacketDetails> {

    List<RedPacketDetailsDTO> findRedPacketDetailsDTOList(Integer wxUsersId);
}
