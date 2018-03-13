package com.plc.pojo;

import java.util.Date;

public class Deposit {
    private Integer id;

    private Date payDate;

    private String receptPosCode;

    private String memName;

    private String nameEng;

    private String courseCode;

    private Integer price;

    private Integer cc1;

    private Integer cc2;

    private String nameParents;

    private String phone;

    private String wechat;

    private String remarks;

    private Boolean isActive;

    private Date createTime;

    private Date updateTime;

    public Deposit(Integer id, Date payDate, String receptPosCode, String memName, String nameEng, String courseCode, Integer price, Integer cc1, Integer cc2, String nameParents, String phone, String wechat, String remarks, Boolean isActive, Date createTime, Date updateTime) {
        this.id = id;
        this.payDate = payDate;
        this.receptPosCode = receptPosCode;
        this.memName = memName;
        this.nameEng = nameEng;
        this.courseCode = courseCode;
        this.price = price;
        this.cc1 = cc1;
        this.cc2 = cc2;
        this.nameParents = nameParents;
        this.phone = phone;
        this.wechat = wechat;
        this.remarks = remarks;
        this.isActive = isActive;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Deposit() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getReceptPosCode() {
        return receptPosCode;
    }

    public void setReceptPosCode(String receptPosCode) {
        this.receptPosCode = receptPosCode == null ? null : receptPosCode.trim();
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName == null ? null : memName.trim();
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng == null ? null : nameEng.trim();
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

    public String getNameParents() {
        return nameParents;
    }

    public void setNameParents(String nameParents) {
        this.nameParents = nameParents == null ? null : nameParents.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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