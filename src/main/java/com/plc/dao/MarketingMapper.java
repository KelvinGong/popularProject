package com.plc.dao;

import com.plc.pojo.Marketing;

import java.util.List;

public interface MarketingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Marketing record);

    int insertSelective(Marketing record);

    Marketing selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Marketing record);

    int updateByPrimaryKey(Marketing record);

    List<Marketing> selectList();

    List<Marketing> selectActiveList();

    int checkMarketingName(String marketingName);
}