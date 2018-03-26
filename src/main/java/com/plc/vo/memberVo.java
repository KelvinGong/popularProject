package com.plc.vo;

import org.apache.commons.codec.language.bm.Rule;

/**
 * Created by gongkelvin on 2018/3/23.
 */
public class MemberVo {

    private Integer id;
    private String memberCode;
    private String memName;
    private String nameEng;
    private String birthday;
    private String gender;
    private String nameParents;
    private String phone;
    private String wechat;
    private String address;
    private String centre;
    private String referFrom;
    private String marketing;
    private String remarks;

    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
        this.memberCode = memberCode;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNameParents() {
        return nameParents;
    }

    public void setNameParents(String nameParents) {
        this.nameParents = nameParents;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCentre() {
        return centre;
    }

    public void setCentre(String centre) {
        this.centre = centre;
    }

    public String getReferFrom() {
        return referFrom;
    }

    public void setReferFrom(String referFrom) {
        this.referFrom = referFrom;
    }

    public String getMarketing() {
        return marketing;
    }

    public void setMarketing(String maketing) {
        this.marketing = maketing;
    }
}
