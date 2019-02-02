package com.huijava.superiorjavablogs.mapper;

public interface AliRedPacketConfigMapperExt {

    Integer getMaxId(Integer id, Integer maxId);

    Integer getMinId(Integer id, Integer minId);

    Integer updateStatusByIdAndVersion(Integer id, Integer status, Integer version);
}