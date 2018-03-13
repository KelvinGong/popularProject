package com.plc.dao;

import com.plc.pojo.Sell;

public interface SellMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sell record);

    int insertSelective(Sell record);

    Sell selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sell record);

    int updateByPrimaryKey(Sell record);
}