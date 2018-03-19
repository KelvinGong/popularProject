package com.plc.dao;

import com.plc.pojo.Class;

import java.util.List;

public interface ClassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Class record);

    int insertSelective(Class record);

    Class selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);

    List<Class> selectList(int centreCode);

    List<Class> selectActiveList(int centreCode);

    int checkClassCode(String classCode);

    int updateLastUser(Integer id, Integer lastUpdateUser);
}