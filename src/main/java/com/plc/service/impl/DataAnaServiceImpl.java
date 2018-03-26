package com.plc.service.impl;

import com.google.common.collect.Lists;
import com.plc.common.Const;
import com.plc.common.ServerResponse;
import com.plc.dao.MemberMapper;
import com.plc.dao.SellMapper;
import com.plc.dao.UserMapper;
import com.plc.pojo.Member;
import com.plc.pojo.Sell;
import com.plc.pojo.User;
import com.plc.service.IDataAnaService;
import com.plc.vo.CcPerformanceVo;
import com.plc.vo.DataAnaCCVo;
import com.plc.vo.DataAnaCtrVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gongkelvin on 2018/3/26.
 */
@Service("iDataAnaService")
public class DataAnaServiceImpl implements IDataAnaService {

    @Autowired
    private SellMapper sellMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MemberMapper memberMapper;


    public ServerResponse ccAna01(Integer centreCode){
        List<User> ccOfCtr = userMapper.selectRoleOfCtr(Const.Role.ROLE_CTR_CC,centreCode);
        List<DataAnaCCVo> dataAnaCCVoList=Lists.newArrayList();
        float totalCtrTurnover=0.0f;
        float totalCtrPerformance=0.0f;
        DataAnaCtrVo dataAnaCtrVo=new DataAnaCtrVo();


        for(User userItem :ccOfCtr){
            List<CcPerformanceVo> newSellVoList= Lists.newArrayList();
            List<CcPerformanceVo> renewalSellVoList = Lists.newArrayList();
            List<Sell> sellListOfCc= sellMapper.selectByKeyword(centreCode,userItem.getId().toString(),"cc1");
            sellListOfCc.add((Sell) sellMapper.selectByKeyword(centreCode,userItem.getId().toString(),"cc2"));
            float newSubtotalTurnover=0.0f;
            float newSubtotalPerformance=0.0f;
            float renewalSubtotalTurnover=0.0f;
            float renewalSubtotalPerformance=0.0f;
            DataAnaCCVo dataAnaCCVo=new DataAnaCCVo();
            float performanceRateF=getPerformanceRateF(newSubtotalTurnover);

            for(Sell sellItem:sellListOfCc){

                if(sellItem.getIsRenewal().equals(Const.isRenewal.IS_RENEWAL_FALSE)){
                    newSellVoList.add(assembleCcPerformanceVo(sellItem));
                    newSubtotalTurnover += assembleCcPerformanceVo(sellItem).getTurnover();
                }else{
                    renewalSellVoList.add(assembleCcPerformanceVo(sellItem));
                    renewalSubtotalTurnover += assembleCcPerformanceVo(sellItem).getTurnover();
                }
            }


            for(CcPerformanceVo ccPerformanceVoItem : newSellVoList){
                ccPerformanceVoItem.setRateF(performanceRateF);
                ccPerformanceVoItem.setTotalPerformancePercent(ccPerformanceVoItem.getRateA()+ccPerformanceVoItem.getRateF());
                ccPerformanceVoItem.setPerformance(ccPerformanceVoItem.getTurnover()*ccPerformanceVoItem.getTotalPerformancePercent());
                newSubtotalPerformance += ccPerformanceVoItem.getPerformance();
            }
            for(CcPerformanceVo ccPerformanceVoItem : renewalSellVoList){
                ccPerformanceVoItem.setTotalPerformancePercent(ccPerformanceVoItem.getRateA()+ccPerformanceVoItem.getRateF());
                ccPerformanceVoItem.setPerformance(ccPerformanceVoItem.getTurnover()*ccPerformanceVoItem.getTotalPerformancePercent());
                renewalSubtotalPerformance += ccPerformanceVoItem.getPerformance();
            }

            dataAnaCCVo.setNewList(newSellVoList);
            dataAnaCCVo.setNewSubtotalTurnover(newSubtotalTurnover);
            dataAnaCCVo.setNewSubtotalPerformance(newSubtotalPerformance);
            dataAnaCCVo.setRenewalList(renewalSellVoList);
            dataAnaCCVo.setRenewalSubtotalTurnover(renewalSubtotalTurnover);
            dataAnaCCVo.setRenewalSubtotalPerformance(renewalSubtotalPerformance);
            dataAnaCCVo.setTotalCCTurnover(dataAnaCCVo.getNewSubtotalTurnover()+dataAnaCCVo.getRenewalSubtotalTurnover());
            dataAnaCCVo.setTotalCCPerformance(dataAnaCCVo.getNewSubtotalPerformance()+dataAnaCCVo.getRenewalSubtotalPerformance());

            dataAnaCCVoList.add(dataAnaCCVo);
            totalCtrTurnover += dataAnaCCVo.getTotalCCTurnover();
            totalCtrPerformance += dataAnaCCVo.getTotalCCPerformance();
        }

        dataAnaCtrVo.setCcList(dataAnaCCVoList);
        dataAnaCtrVo.setTotalCtrTurnover(totalCtrTurnover);
        dataAnaCtrVo.setTotalCtrPerformance(totalCtrPerformance);

        return ServerResponse.createBySuccess(dataAnaCtrVo);
    }

    private CcPerformanceVo assembleCcPerformanceVo(Sell sell){
        CcPerformanceVo ccPerformanceVo=new CcPerformanceVo();
        ccPerformanceVo.setMemberName(memberMapper.selectByPrimaryKey(sell.getMemberCode()).getMemName());
        if(sell.getCc2()==null || sell.getCc1()==null){
            ccPerformanceVo.setTurnover(sell.getPrice());
        }else{
            ccPerformanceVo.setTurnover(sell.getPrice()/2);
        }
        if(sell.getIsRenewal().equals(Const.isRenewal.IS_RENEWAL_TRUE)){
            ccPerformanceVo.setRateA(Const.isRenewal.RENEWAL_RATE);
            ccPerformanceVo.setRateF(0.0f);
        }else{
            ccPerformanceVo.setRateA(Const.isRenewal.NEW_RATE);
        }

        return ccPerformanceVo;
    }

    private float getPerformanceRateF(float totalCcTurnover){
        if(totalCcTurnover<=50000){
            return 0.0f;
        }else if(totalCcTurnover<=100000){
            return 0.015f;
        }else if(totalCcTurnover<=150000){
            return 0.025f;
        }else if(totalCcTurnover<=200000){
            return 0.03f;
        }else if(totalCcTurnover<=250000){
            return 0.035f;
        }else{
            return 0.04f;
        }
    }


}
