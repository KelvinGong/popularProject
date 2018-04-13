package com.plc.service;

import com.github.pagehelper.PageInfo;
import com.plc.common.ServerResponse;
import com.plc.pojo.Sell;
import com.plc.vo.SellVo;

/**
 * Created by gongkelvin on 2018/3/19.
 */
public interface ISellService {
    ServerResponse<PageInfo> listSell(Integer centreCode,
                                      String keyword,
                                      String field,
                                      int pageNum,
                                      int pageSize,
                                      String orderByField,
                                      String orderBy,
                                      String startDate,
                                      String endDate,
                                      String dateField);

    ServerResponse<String> addSell(Sell sell);

    ServerResponse<Sell> updateMember(Sell sell);
    ServerResponse<SellVo> selectById(Integer id);



}
