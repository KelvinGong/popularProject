package com.plc.pojo;

import java.util.Date;

public class Sell {
    private Integer id;

    private Integer ctrCode;

    private Date payDate;

    private Integer memberCode;

    private String contractCode;

    private String receptPosCode;

    private String courseCode;

    private Integer price;

    private Integer status;

    private String classCode;

    private Boolean isRenewal;

    private Boolean isShort;

    private Date firstclassTime;

    private Integer dayOfWeek;

    private Integer cc1;

    private Integer cc2;

    private Integer ei;

    private Integer ta;

    private Integer ii;

    private String remarks;

    private Date pauseDate;

    private Date graduateDate;

    private Date refundDate;

    private Date createTime;

    private Date updateTime;

    public Sell(Integer id, Integer ctrCode, Date payDate, Integer memberCode, String contractCode, String receptPosCode, String courseCode, Integer price, Integer status, String classCode, Boolean isRenewal, Boolean isShort, Date firstclassTime, Integer dayOfWeek, Integer cc1, Integer cc2, Integer ei, Integer ta, Integer ii, String remarks, Date pauseDate, Date graduateDate, Date refundDate, Date createTime, Date updateTime) {
        this.id = id;
        this.ctrCode = ctrCode;
        this.payDate = payDate;
        this.memberCode = memberCode;
        this.contractCode = contractCode;
        this.receptPosCode = receptPosCode;
        this.courseCode = courseCode;
        this.price = price;
        this.status = status;
        this.classCode = classCode;
        this.isRenewal = isRenewal;
        this.isShort = isShort;
        this.firstclassTime = firstclassTime;
        this.dayOfWeek = dayOfWeek;
        this.cc1 = cc1;
        this.cc2 = cc2;
        this.ei = ei;
        this.ta = ta;
        this.ii = ii;
        this.remarks = remarks;
        this.pauseDate = pauseDate;
        this.graduateDate = graduateDate;
        this.refundDate = refundDate;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Sell() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCtrCode() {
        return ctrCode;
    }

    public void setCtrCode(Integer ctrCode) {
        this.ctrCode = ctrCode;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Integer getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(Integer memberCode) {
        this.memberCode = memberCode;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode == null ? null : contractCode.trim();
    }

    public String getReceptPosCode() {
        return receptPosCode;
    }

    public void setReceptPosCode(String receptPosCode) {
        this.receptPosCode = receptPosCode == null ? null : receptPosCode.trim();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode == null ? null : courseCode.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode == null ? null : classCode.trim();
    }

    public Boolean getIsRenewal() {
        return isRenewal;
    }

    public void setIsRenewal(Boolean isRenewal) {
        this.isRenewal = isRenewal;
    }

    public Boolean getIsShort() {
        return isShort;
    }

    public void setIsShort(Boolean isShort) {
        this.isShort = isShort;
    }

    public Date getFirstclassTime() {
        return firstclassTime;
    }

    public void setFirstclassTime(Date firstclassTime) {
        this.firstclassTime = firstclassTime;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getCc1() {
        return cc1;
    }

    public void setCc1(Integer cc1) {
        this.cc1 = cc1;
    }

    public Integer getCc2() {
        return cc2;
    }

    public void setCc2(Integer cc2) {
        this.cc2 = cc2;
    }

    public Integer getEi() {
        return ei;
    }

    public void setEi(Integer ei) {
        this.ei = ei;
    }

    public Integer getTa() {
        return ta;
    }

    public void setTa(Integer ta) {
        this.ta = ta;
    }

    public Integer getIi() {
        return ii;
    }

    public void setIi(Integer ii) {
        this.ii = ii;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Date getPauseDate() {
        return pauseDate;
    }

    public void setPauseDate(Date pauseDate) {
        this.pauseDate = pauseDate;
    }

    public Date getGraduateDate() {
        return graduateDate;
    }

    public void setGraduateDate(Date graduateDate) {
        this.graduateDate = graduateDate;
    }

    public Date getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(Date refundDate) {
        this.refundDate = refundDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}