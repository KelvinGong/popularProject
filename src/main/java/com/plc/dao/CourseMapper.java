package com.plc.dao;

import com.plc.pojo.Course;

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

    int updateLastUser(Integer id, Integer lastUpdateUser);
}