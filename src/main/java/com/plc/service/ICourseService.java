package com.plc.service;

import com.github.pagehelper.PageInfo;
import com.plc.common.ServerResponse;
import com.plc.pojo.Course;

/**
 * Created by gongkelvin on 2018/3/16.
 */
public interface ICourseService {

    ServerResponse<PageInfo> getCourseList(int pageNum, int pageSize);
    ServerResponse getActiveCourseList();
    ServerResponse<String> addCourse(Course course,Integer currentUserId);
    ServerResponse<Course> updateCourse(Course course,Integer currentUserId);
}
