package com.plc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plc.common.Const;
import com.plc.common.ResponseCode;
import com.plc.common.ServerResponse;
import com.plc.dao.SellMapper;
import com.plc.pojo.Sell;
import com.plc.service.ISellService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by gongkelvin on 2018/3/19.
 */
@Service("iSellService")
public class SellServiceImpl implements ISellService {

    @Autowired
    private SellMapper sellMapper;

    public ServerResponse<PageInfo> listSell(Integer centreCode,
                                               String keyword,
                                               String field,
                                               int pageNum,
                                               int pageSize,
                                               String orderByField,
                                               String orderBy){
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
        List<Sell> sellList = sellMapper.selectByKeyword(centreCode,StringUtils.isBlank(keyword)?null:keyword,StringUtils.isBlank(field)?null:field);

        PageInfo pageInfo = new PageInfo(sellList);
        //pageInfo.setList(productListVoList);
        return ServerResponse.createBySuccess(pageInfo);
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
