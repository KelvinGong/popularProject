package com.plc.pojo;

import java.util.Date;

public class Centre {
    private Integer id;

    private String centreCode;

    private String centreName;

    private String centreRegion;

    private String address;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    public Centre(Integer id, String centreCode, String centreName, String centreRegion, String address, Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.centreCode = centreCode;
        this.centreName = centreName;
        this.centreRegion = centreRegion;
        this.address = address;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Centre() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCentreCode() {
        return centreCode;
    }

    public void setCentreCode(String centreCode) {
        this.centreCode = centreCode == null ? null : centreCode.trim();
    }

    public String getCentreName() {
        return centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName == null ? null : centreName.trim();
    }

    public String getCentreRegion() {
        return centreRegion;
    }

    public void setCentreRegion(String centreRegion) {
        this.centreRegion = centreRegion == null ? null : centreRegion.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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