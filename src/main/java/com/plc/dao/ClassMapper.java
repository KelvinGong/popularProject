package com.plc.dao;

import com.plc.pojo.Class;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Class record);

    int insertSelective(Class record);

    Class selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);

    List<Class> selectList();
    List<Class> selectList(@Param("ctrCode") Integer ctrCode);

    List<Class> selectActiveList( @Param("ctrCode") Integer ctrCode);

    int checkClassCode(String classCode);

    int updateLastUser(@Param("id") Integer id, @Param("lastUpdateUser") Integer lastUpdateUser);
}