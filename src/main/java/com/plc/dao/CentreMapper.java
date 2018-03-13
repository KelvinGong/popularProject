package com.plc.dao;

import com.plc.pojo.Centre;

public interface CentreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Centre record);

    int insertSelective(Centre record);

    Centre selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Centre record);

    int updateByPrimaryKey(Centre record);
}