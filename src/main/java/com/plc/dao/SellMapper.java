package com.plc.dao;

import com.plc.pojo.Sell;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SellMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Sell record);

    int insertSelective(Sell record);

    Sell selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sell record);

    int updateByPrimaryKey(Sell record);

    List<Sell> selectByKeyword(@Param("centreCode") int centreCode, @Param("keyword") String keyword, @Param("field")String field);


}