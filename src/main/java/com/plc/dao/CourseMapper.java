package com.plc.dao;

import com.plc.pojo.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    List<Course> selectList();

    List<Course> selectActiveList();

    int checkCourseCode(String courseCode);

    int updateLastUser(@Param("id") Integer id, @Param("lastUpdateUser") Integer lastUpdateUser);
}