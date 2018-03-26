package com.plc.vo;

import java.util.List;

/**
 * Created by gongkelvin on 2018/3/26.
 */
public class DataAnaCtrVo {


    private List<DataAnaCCVo> ccList;
    private Float totalCtrTurnover;
    private Float totalCtrPerformance;

    public Float getTotalCtrTurnover() {
        return totalCtrTurnover;
    }

    public void setTotalCtrTurnover(Float totalCtrTurnover) {
        this.totalCtrTurnover = totalCtrTurnover;
    }

    public Float getTotalCtrPerformance() {
        return totalCtrPerformance;
    }

    public void setTotalCtrPerformance(Float totalCtrPerformance) {
        this.totalCtrPerformance = totalCtrPerformance;
    }

    public List<DataAnaCCVo> getCcList() {
        return ccList;
    }

    public void setCcList(List<DataAnaCCVo> ccList) {
        this.ccList = ccList;
    }
}
