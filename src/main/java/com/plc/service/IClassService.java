package com.plc.service;

import com.github.pagehelper.PageInfo;
import com.plc.common.ServerResponse;
import com.plc.pojo.Class;

/**
 * Created by gongkelvin on 2018/3/19.
 */
public interface IClassService {

    ServerResponse<PageInfo> getClassList(int pageNum, int pageSize);

    ServerResponse<PageInfo> getClassList(int pageNum, int pageSize, Integer centreCode);

    ServerResponse getActiveClassList(Integer ctrCode);

    ServerResponse<Class> updateClass(Class classOB, Integer currentUserId);

    ServerResponse<String> addClass(Class classOB,Integer currentUserId);
}
