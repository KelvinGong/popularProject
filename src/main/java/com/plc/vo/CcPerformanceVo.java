package com.plc.vo;

/**
 * Created by gongkelvin on 2018/3/26.
 */
public class CcPerformanceVo {

    private String ccName;
    private String memberName;
    private Integer turnover;
    private float rateA;
    private float rateF;
    private float totalPerformancePercent;
    private float performance;

    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getTurnover() {
        return turnover;
    }

    public void setTurnover(Integer turnover) {
        this.turnover = turnover;
    }

    public float getTotalPerformancePercent() {
        return totalPerformancePercent;
    }

    public void setTotalPerformancePercent(float totalPerformancePercent) {
        this.totalPerformancePercent = totalPerformancePercent;
    }

    public float getPerformance() {
        return performance;
    }

    public void setPerformance(float performance) {
        this.performance = performance;
    }

    public float getRateA() {
        return rateA;
    }

    public void setRateA(float rateA) {
        this.rateA = rateA;
    }

    public float getRateF() {
        return rateF;
    }

    public void setRateF(float rateF) {
        this.rateF = rateF;
    }
}
