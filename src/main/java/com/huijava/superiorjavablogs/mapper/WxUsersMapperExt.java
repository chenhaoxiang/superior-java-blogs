package com.huijava.superiorjavablogs.mapper;

import com.huijava.superiorjavablogs.dto.WxUsersDTO;

import java.util.List;

public interface WxUsersMapperExt {
    /**
     * @param pid
     * @return
     */
    List<WxUsersDTO> findWxUsersDTOByPid(Integer pid);

}