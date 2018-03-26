package com.plc.dao;

import com.plc.pojo.Centre;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CentreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Centre record);

    int insertSelective(Centre record);

    Centre selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Centre record);

    int updateByPrimaryKey(Centre record);

    List<Centre> selectList();

    List<Centre> selectActiveList();

    int checkCtrCode(String centreCode);

}