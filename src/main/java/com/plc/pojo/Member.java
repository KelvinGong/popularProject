package com.plc.pojo;

import java.util.Date;

public class Member {
    private Integer id;

    private String memberCode;

    private String memName;

    private String nameEng;

    private Date birthday;

    private String gender;

    private String nameParents;

    private String phone;

    private String wechat;

    private String address;

    private String centre;

    private String referFrom;

    private Integer marketing;

    private String remarks;

    private Date createTime;

    private Date updateTime;

    public Member(Integer id, String memberCode, String memName, String nameEng, Date birthday, String gender, String nameParents, String phone, String wechat, String address, String centre, String referFrom, Integer marketing, String remarks, Date createTime, Date updateTime) {
        this.id = id;
        this.memberCode = memberCode;
        this.memName = memName;
        this.nameEng = nameEng;
        this.birthday = birthday;
        this.gender = gender;
        this.nameParents = nameParents;
        this.phone = phone;
        this.wechat = wechat;
        this.address = address;
        this.centre = centre;
        this.referFrom = referFrom;
        this.marketing = marketing;
        this.remarks = remarks;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Member() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode == null ? null : memberCode.trim();
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre == null ? null : centre.trim();
    }

    public String getReferFrom() {
        return referFrom;
    }

    public void setReferFrom(String referFrom) {
        this.referFrom = referFrom == null ? null : referFrom.trim();
    }

    public Integer getMarketing() {
        return marketing;
    }

    public void setMarketing(Integer marketing) {
        this.marketing = marketing;
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
}