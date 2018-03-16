package com.plc.pojo;

import java.util.Date;

public class Course {
    private Integer id;

    private String courseCode;

    private String courseDesc;

    private Integer price;

    private Integer status;

    private Boolean isShort;

    private String remarks;

    private Date createTime;

    private Date updateTime;

    private Integer lastUpdateUser;

    public Course(Integer id, String courseCode, String courseDesc, Integer price, Integer status, Boolean isShort, String remarks, Date createTime, Date updateTime, Integer lastUpdateUser) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseDesc = courseDesc;
        this.price = price;
        this.status = status;
        this.isShort = isShort;
        this.remarks = remarks;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.lastUpdateUser = lastUpdateUser;
    }

    public Course() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode == null ? null : courseCode.trim();
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc == null ? null : courseDesc.trim();
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

    public Boolean getIsShort() {
        return isShort;
    }

    public void setIsShort(Boolean isShort) {
        this.isShort = isShort;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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

    public Integer getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(Integer lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }
}