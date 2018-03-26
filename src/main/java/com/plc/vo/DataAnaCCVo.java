package com.plc.vo;

import java.util.List;

/**
 * Created by gongkelvin on 2018/3/26.
 */
public class DataAnaCCVo {


    private List<CcPerformanceVo> newList;
    private Float newSubtotalTurnover;
    private Float newSubtotalPerformance;
    private List<CcPerformanceVo> renewalList;
    private Float renewalSubtotalTurnover;
    private Float renewalSubtotalPerformance;
    private Float totalCCTurnover;
    private Float totalCCPerformance;

    public Float getTotalCCTurnover() {
        return totalCCTurnover;
    }

    public void setTotalCCTurnover(Float totalCCTurnover) {
        this.totalCCTurnover = totalCCTurnover;
    }

    public Float getTotalCCPerformance() {
        return totalCCPerformance;
    }

    public void setTotalCCPerformance(Float totalCCPerformance) {
        this.totalCCPerformance = totalCCPerformance;
    }

    public List<CcPerformanceVo> getNewList() {
        return newList;
    }

    public void setNewList(List<CcPerformanceVo> newList) {
        this.newList = newList;
    }

    public Float getNewSubtotalTurnover() {
        return newSubtotalTurnover;
    }

    public void setNewSubtotalTurnover(Float newSubtotalTurnover) {
        this.newSubtotalTurnover = newSubtotalTurnover;
    }

    public Float getNewSubtotalPerformance() {
        return newSubtotalPerformance;
    }

    public void setNewSubtotalPerformance(Float newSubtotalPerformance) {
        this.newSubtotalPerformance = newSubtotalPerformance;
    }

    public List<CcPerformanceVo> getRenewalList() {
        return renewalList;
    }

    public void setRenewalList(List<CcPerformanceVo> renewalList) {
        this.renewalList = renewalList;
    }

    public Float getRenewalSubtotalTurnover() {
        return renewalSubtotalTurnover;
    }

    public void setRenewalSubtotalTurnover(Float renewalSubtotalTurnover) {
        this.renewalSubtotalTurnover = renewalSubtotalTurnover;
    }

    public Float getRenewalSubtotalPerformance() {
        return renewalSubtotalPerformance;
    }

    public void setRenewalSubtotalPerformance(Float renewalSubtotalPerformance) {
        this.renewalSubtotalPerformance = renewalSubtotalPerformance;
    }
}
