package com.plc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.plc.common.Const;
import com.plc.common.ResponseCode;
import com.plc.common.ServerResponse;
import com.plc.dao.MarketingMapper;
import com.plc.dao.MemberMapper;
import com.plc.pojo.Member;
import com.plc.service.ICentreService;
import com.plc.service.IMemberService;
import com.plc.util.DateTimeUtil;
import com.plc.util.PropertiesUtil;
import com.plc.vo.MemberVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.apache.commons.lang3.time.DateUtils.*;

/**
 * Created by gongkelvin on 2018/3/15.
 */
@Service("iMemberService")
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private ICentreService centreService;

    @Autowired
    private MarketingMapper marketingMapper;



    public ServerResponse<PageInfo> listMember(Integer centreCode,
                                               String keyword,
                                               String field,
                                               int pageNum,
                                               int pageSize,
                                               String orderByField,
                                               String orderBy
                                               ){
        //startPage--start
        //填充自己的sql查询逻辑
        //pageHelper-收尾

        if(StringUtils.isNotBlank(keyword) &&  field== null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        if(StringUtils.isBlank(keyword)&& field !=null){
            field=null;
        }
        PageHelper.startPage(pageNum,pageSize);
        //排序处理
        if(StringUtils.isNotBlank(orderBy)) {
            //校验排序语句
            if(Const.memberOrderBy.ORDER_FIELD.contains(orderByField)){
                if(Const.memberOrderBy.ORDER.contains(orderBy)) {
                    //String[] orderByArray = orderBy.split("_");
                    PageHelper.orderBy(orderByField+" "+orderBy);
                }else{
                    PageHelper.orderBy(orderByField+" asc");
                }

            }


        }
        if(StringUtils.isNotBlank(keyword)){
            keyword = new StringBuilder().append("%").append(keyword).append("%").toString();
        }
        List<Member> memberList = memberMapper.selectByKeyword(centreCode,StringUtils.isBlank(keyword)?null:keyword,StringUtils.isBlank(field)?null:field);

        List<MemberVo> memberVoList = Lists.newArrayList();
        for(Member memberItem : memberList){
            MemberVo memberVo = assembleMemberVo(memberItem);
            memberVoList.add(memberVo);
        }
        PageInfo pageResult = new PageInfo(memberList);
        pageResult.setList(memberVoList);


        //PageInfo pageInfo = new PageInfo(memberList);
        //pageInfo.setList(productListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    private MemberVo assembleMemberVo(Member member){
        MemberVo memberVo=new MemberVo();
        memberVo.setId(member.getId());
        memberVo.setMemberCode(member.getMemberCode());
        memberVo.setMemName(member.getMemName());
        memberVo.setNameEng(member.getNameEng());

        //DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        //String birthdayDate= dateFormat.format(member.getBirthday());
        memberVo.setBirthday(DateTimeUtil.dateToStr(member.getBirthday(),"yyyy-MM-dd"));
        memberVo.setAge(DateTimeUtil.getAgeByBirth(member.getBirthday()));

        memberVo.setGender(member.getGender());
        memberVo.setNameParents(member.getNameParents());
        memberVo.setPhone(member.getPhone());
        memberVo.setWechat(member.getWechat());
        memberVo.setAddress(member.getAddress());

        String centreCode=centreService.getCentreName(member.getCentre());
        memberVo.setCentre(centreCode);

        memberVo.setReferFrom(member.getReferFrom());

        memberVo.setMarketing(marketingMapper.selectByPrimaryKey(member.getMarketing()).getMarketingName());
        memberVo.setMarketingCode(member.getMarketing());

        memberVo.setRemarks(member.getRemarks());



//        productDetailVo.setCreateTime(DateTimeUtil.dateToStr(product.getCreateTime()));
//        productDetailVo.setUpdateTime(DateTimeUtil.dateToStr(product.getUpdateTime()));
        return memberVo;
    }

    public ServerResponse<MemberVo> selectById(Integer id){
        Member tempMember= memberMapper.selectByPrimaryKey(id);
        if(tempMember==null){
            return ServerResponse.createByErrorMessage("参数错误(id未找到)");
        }
        MemberVo memberVo=assembleMemberVo(tempMember);
        return ServerResponse.createBySuccess(memberVo);
    }

    public ServerResponse<String> addMember(Member member){
        ServerResponse validResponse = this.checkValid(member.getMemberCode());
        if(!validResponse.isSuccess()){
            return validResponse;
        }
        int resultCount = memberMapper.insert(member);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("添加会员失败");
        }
        return ServerResponse.createBySuccessMessage("添加会员成功");
    }

    public ServerResponse<String> checkValid(String str){
        //开始校验

        int resultCount = memberMapper.checkMemberCode(str);
        if(resultCount > 0 ){
            return ServerResponse.createByErrorMessage("会员号已存在");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    public ServerResponse<Member> updateMember(Member member){

        //Centrename是不能被更新的
        //email也要进行一个校验,校验新的email是不是已经存在,并且存在的email如果相同的话,不能是我们当前的这个用户的.
        Member tempMember= memberMapper.selectByPrimaryKey(member.getId());
        if(tempMember==null){
            return ServerResponse.createByErrorMessage("参数错误(id未匹配)");
        }
        String oldname=tempMember.getMemberCode();
        //把当前id的渠道名修改为不可重复值
        Member updateMember = new Member();
        updateMember.setId(tempMember.getId());


        //判断marketingname是否是重复值
        if(member.getMemberCode()!=null){
            updateMember.setMemberCode(UUID.randomUUID().toString());
            int updateCount = memberMapper.updateByPrimaryKeySelective(updateMember);
            if(updateCount>0){
                ServerResponse validResponse = this.checkValid(member.getMemberCode());
                if(!validResponse.isSuccess()){
                    //校验失败,将原marketing值写回,并且报告校验失败
                    updateMember.setMemberCode(oldname);
                    updateCount = memberMapper.updateByPrimaryKeySelective(updateMember);
                    if(updateCount > 0){
                        return validResponse;
                    }
                }
            }

            updateMember.setMemberCode(member.getMemberCode());
        }


        //校验成功,将新值写入

        updateMember.setMemName(member.getMemName());
        updateMember.setNameEng(member.getNameEng());
        updateMember.setBirthday(member.getBirthday());
        updateMember.setGender(member.getGender());
        updateMember.setNameParents(member.getNameParents());
        updateMember.setPhone(member.getPhone());
        updateMember.setWechat(member.getWechat());
        updateMember.setAddress(member.getAddress());
        updateMember.setCentre(member.getCentre());
        updateMember.setReferFrom(member.getReferFrom());
        updateMember.setMarketing(member.getMarketing());
        updateMember.setRemarks(member.getRemarks());

        int updateCount = memberMapper.updateByPrimaryKeySelective(updateMember);
        if(updateCount > 0){
            return ServerResponse.createBySuccess("更新会员信息成功",memberMapper.selectByPrimaryKey(member.getId()));
        }
        return ServerResponse.createByErrorMessage("更新会员信息失败");
    }
}
