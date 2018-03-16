package com.plc.pojo;

import java.util.Date;

public class Class {
    private Integer id;

    private String classCode;

    private Integer ctrCode;

    private Integer courseCode;

    private Integer status;

    private Date firstclassTime;

    private Date endclassTime;

    private Integer dayOfWeek;

    private Integer ei;

    private Integer ta;

    private Integer ii;

    private String remarks;

    private Date createTime;

    private Date updateTime;

    private Integer lastUpdateUser;

    public Class(Integer id, String classCode, Integer ctrCode, Integer courseCode, Integer status, Date firstclassTime, Date endclassTime, Integer dayOfWeek, Integer ei, Integer ta, Integer ii, String remarks, Date createTime, Date updateTime, Integer lastUpdateUser) {
        this.id = id;
        this.classCode = classCode;
        this.ctrCode = ctrCode;
        this.courseCode = courseCode;
        this.status = status;
        this.firstclassTime = firstclassTime;
        this.endclassTime = endclassTime;
        this.dayOfWeek = dayOfWeek;
        this.ei = ei;
        this.ta = ta;
        this.ii = ii;
        this.remarks = remarks;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.lastUpdateUser = lastUpdateUser;
    }

    public Class() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode == null ? null : classCode.trim();
    }

    public Integer getCtrCode() {
        return ctrCode;
    }

    public void setCtrCode(Integer ctrCode) {
        this.ctrCode = ctrCode;
    }

    public Integer getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(Integer courseCode) {
        this.courseCode = courseCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getFirstclassTime() {
        return firstclassTime;
    }

    public void setFirstclassTime(Date firstclassTime) {
        this.firstclassTime = firstclassTime;
    }

    public Date getEndclassTime() {
        return endclassTime;
    }

    public void setEndclassTime(Date endclassTime) {
        this.endclassTime = endclassTime;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
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