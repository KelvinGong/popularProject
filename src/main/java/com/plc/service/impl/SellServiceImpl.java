package com.plc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.plc.common.Const;
import com.plc.common.ResponseCode;
import com.plc.common.ServerResponse;
import com.plc.dao.MemberMapper;
import com.plc.dao.SellMapper;
import com.plc.dao.UserMapper;
import com.plc.pojo.Member;
import com.plc.pojo.Sell;
import com.plc.service.ISellService;
import com.plc.util.DateTimeUtil;
import com.plc.vo.MemberVo;
import com.plc.vo.SellVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by gongkelvin on 2018/3/19.
 */
@Service("iSellService")
public class SellServiceImpl implements ISellService {

    @Autowired
    private SellMapper sellMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private UserMapper userMapper;

    public ServerResponse<PageInfo> listSell(Integer centreCode,
                                             String keyword,
                                             String field,
                                             int pageNum,
                                             int pageSize,
                                             String orderByField,
                                             String orderBy,
                                             String startDate,
                                             String endDate,
                                             String dateField){
        //startPage--start
        //填充自己的sql查询逻辑
        //pageHelper-收尾

        if(StringUtils.isNotBlank(keyword) &&  field== null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        //时间模块校验
        if(dateField!=null){
            if(Const.dateField.SELL_DATE_FIELD.contains(dateField)){
                if(DateTimeUtil.strToDate(startDate,"yyyy-MM-dd").getTime() > DateTimeUtil.strToDate(endDate,"yyyy-MM-dd").getTime()){
                    return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
                }

            }else{
                return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
            }
        }
        if(StringUtils.isBlank(keyword)&& field !=null){
            field=null;
        }
        PageHelper.startPage(pageNum,pageSize);
        //排序处理
        if(StringUtils.isNotBlank(orderBy)) {
            //校验排序语句
            if(Const.sellOrderBy.ORDER_FIELD.contains(orderByField)){
                if(Const.sellOrderBy.ORDER.contains(orderBy)) {
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
        List<Sell> sellList = sellMapper.selectByKeyword(centreCode,StringUtils.isBlank(keyword)?null:keyword,StringUtils.isBlank(field)?null:field,startDate,endDate,dateField);
        List<SellVo> sellVoList = Lists.newArrayList();
        for(Sell sellItem : sellList){
            SellVo sellVo = assembleSellVo(sellItem);
            sellVoList.add(sellVo);
        }
        PageInfo pageInfo = new PageInfo(sellList);
        pageInfo.setList(sellVoList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    private SellVo assembleSellVo(Sell sell){


        SellVo sellVo = new SellVo();
        sellVo.setId(sell.getId());
        sellVo.setPayDate(DateTimeUtil.dateToStr(sell.getPayDate(),"yyyy-MM-dd"));
        sellVo.setMemberName(memberMapper.selectByPrimaryKey(sell.getMemberCode()).getMemName());
        sellVo.setMemberCode(memberMapper.selectByPrimaryKey(sell.getMemberCode()).getMemberCode());
        sellVo.setContractCode(sell.getContractCode());
        sellVo.setReceptPosCode(sell.getReceptPosCode());
        sellVo.setPrice(sell.getPrice().toString());
        sellVo.set_classCode(sell.getClassCode());//// TODO: 2018/4/2 需要配置class
        sellVo.setRenewal(sell.getIsRenewal());
        sellVo.setShort(sell.getIsShort());
        sellVo.setFirstclassTime(DateTimeUtil.dateToStr(sell.getFirstclassTime(),"yyyy-MM-dd"));
        //sellVo.setDayOfWeek(sell.getDayOfWeek().toString());//// TODO: 2018/4/2
        sellVo.setCc1(sell.getCc1()==null?null:userMapper.selectByPrimaryKey(sell.getCc1()).getStaffName());
        sellVo.setCc2(sell.getCc2()==null?null:userMapper.selectByPrimaryKey(sell.getCc2()).getStaffName());
        sellVo.setEi(sell.getEi()==null?null:userMapper.selectByPrimaryKey(sell.getEi()).getStaffName());
        sellVo.setTa(sell.getTa()==null?null:userMapper.selectByPrimaryKey(sell.getTa()).getStaffName());
        sellVo.setIi(sell.getIi()==null?null:userMapper.selectByPrimaryKey(sell.getIi()).getStaffName());
        sellVo.setRemarks(sell.getRemarks());
        sellVo.setPauseDate(DateTimeUtil.dateToStr(sell.getPauseDate(),"yyyy-MM-dd"));
        sellVo.setGraduateDate(DateTimeUtil.dateToStr(sell.getGraduateDate(),"yyyy-MM-dd"));
        sellVo.setRefundDate(DateTimeUtil.dateToStr(sell.getRefundDate(),"yyyy-MM-dd"));
        return sellVo;
    }

    public ServerResponse<String> addSell(Sell sell){
/*        ServerResponse validResponse = this.checkValid(sell.getMemberCode());
        if(!validResponse.isSuccess()){
            return validResponse;
        }*/
        int resultCount = sellMapper.insert(sell);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("添加销售单失败");
        }
        return ServerResponse.createBySuccessMessage("添加销售单成功");
    }

/*    public ServerResponse<String> checkValid(String str){
        //开始校验

        int resultCount = memberMapper.checkMemberCode(str);
        if(resultCount > 0 ){
            return ServerResponse.createByErrorMessage("会员号已存在");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }*/

    public ServerResponse<Sell> updateMember(Sell sell){

        //Centrename是不能被更新的
        //email也要进行一个校验,校验新的email是不是已经存在,并且存在的email如果相同的话,不能是我们当前的这个用户的.
        Sell tempSell= sellMapper.selectByPrimaryKey(sell.getId());
        if(tempSell==null){
            return ServerResponse.createByErrorMessage("参数错误(id未匹配)");
        }
/*        String oldname=tempSell.getMemberCode();
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
        }*/


        //校验成功,将新值写入

        Sell updateSell=new Sell();
        updateSell.setMemberCode(sell.getMemberCode());


        int updateCount = sellMapper.updateByPrimaryKeySelective(updateSell);
        if(updateCount > 0){
            return ServerResponse.createBySuccess("更新会员信息成功",sellMapper.selectByPrimaryKey(sell.getId()));
        }
        return ServerResponse.createByErrorMessage("更新会员信息失败");
    }
}
