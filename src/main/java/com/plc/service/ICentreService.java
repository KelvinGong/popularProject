package com.plc.service;

import com.github.pagehelper.PageInfo;
import com.plc.common.ServerResponse;
import com.plc.pojo.Centre;

/**
 * Created by gongkelvin on 2018/3/14.
 */
public interface ICentreService {

    ServerResponse<PageInfo> getCentreList(int pageNum, int pageSize);

    ServerResponse getActiveCentreList();

    ServerResponse<String> newCentre(Centre centre);

    ServerResponse<Centre> updateCentre(Centre centre);
}
