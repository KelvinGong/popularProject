package com.plc.service;

import com.github.pagehelper.PageInfo;
import com.plc.common.ServerResponse;
import com.plc.pojo.Marketing;

/**
 * Created by gongkelvin on 2018/3/14.
 */
public interface IMarketingService {

    ServerResponse<PageInfo> getMarketingList(int pageNum, int pageSize);

    ServerResponse getActiveMarketingList();

    ServerResponse<Marketing> updateMarketing(Marketing marketing);

    ServerResponse<String> addMarketing(Marketing marketing);

}
