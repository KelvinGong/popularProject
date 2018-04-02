package com.plc.service;

import com.plc.common.ServerResponse;

/**
 * Created by gongkelvin on 2018/3/26.
 */
public interface IDataAnaService {

    ServerResponse ccAna01(Integer centreCode,String startDate, String endDate, String dateField);
}
