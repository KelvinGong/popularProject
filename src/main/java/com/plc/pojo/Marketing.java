package com.plc.pojo;

import java.util.Date;

public class Marketing {
    private Integer id;

    private String marketingName;

    private Integer marketingClass;

    private Boolean status;

    private Date createTime;

    private Date updateTime;

    public Marketing(Integer id, String marketingName, Integer marketingClass, Boolean status, Date createTime, Date updateTime) {
        this.id = id;
        this.marketingName = marketingName;
        this.marketingClass = marketingClass;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Marketing() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName == null ? null : marketingName.trim();
    }

    public Integer getMarketingClass() {
        return marketingClass;
    }

    public void setMarketingClass(Integer marketingClass) {
        this.marketingClass = marketingClass;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
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