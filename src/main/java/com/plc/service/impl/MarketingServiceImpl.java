package com.plc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plc.common.ServerResponse;
import com.plc.dao.MarketingMapper;
import com.plc.pojo.Marketing;
import com.plc.service.IMarketingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by gongkelvin on 2018/3/14.
 */
@Service("iMarketingService")
public class MarketingServiceImpl implements IMarketingService {

    @Autowired
    private MarketingMapper marketingMapper;

    /**
     * 用于显示渠道列表,无status校验,有分页插件
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse<PageInfo> getMarketingList(int pageNum, int pageSize){
        //startPage--start
        //填充自己的sql查询逻辑
        //pageHelper-收尾
        PageHelper.startPage(pageNum,pageSize);
        List<Marketing> centreList = marketingMapper.selectList();

/*        List<ProductListVo> productListVoList = Lists.newArrayList();
        for(Product productItem : productList){
            ProductListVo productListVo = assembleProductListVo(productItem);
            productListVoList.add(productListVo);
        }*/
        PageInfo pageResult = new PageInfo(centreList);
        //pageResult.setList(productListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    /**
     * 用于显示渠道列表,有status校验,无分页插件
     * @return
     */
    public ServerResponse getActiveMarketingList(){
        List<Marketing> centreList = marketingMapper.selectActiveList();
        return ServerResponse.createBySuccess(centreList);
    }

    public ServerResponse<String> addMarketing(Marketing marketing){
        ServerResponse validResponse = this.checkValid(marketing.getMarketingName());
        if(!validResponse.isSuccess()){
            return validResponse;
        }
        int resultCount = marketingMapper.insert(marketing);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("添加渠道失败");
        }
        return ServerResponse.createBySuccessMessage("添加渠道成功");
    }

    /**
     * 校验方法
     * @param str 渠道名字
     * @return
     */
    public ServerResponse<String> checkValid(String str){
        //开始校验

        int resultCount = marketingMapper.checkMarketingName(str);
        if(resultCount > 0 ){
            return ServerResponse.createByErrorMessage("渠道已存在");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    public ServerResponse<Marketing> updateMarketing(Marketing marketing){
        //Centrename是不能被更新的
        //email也要进行一个校验,校验新的email是不是已经存在,并且存在的email如果相同的话,不能是我们当前的这个用户的.
        Marketing tempMarketing= marketingMapper.selectByPrimaryKey(marketing.getId());
        if(tempMarketing==null){
            return ServerResponse.createByErrorMessage("参数错误(id未匹配)");
        }
        String oldname=tempMarketing.getMarketingName();
        //把当前id的渠道名修改为不可重复值
        Marketing updateMarketing = new Marketing();
        updateMarketing.setId(marketing.getId());
        updateMarketing.setMarketingName(UUID.randomUUID().toString());

        int updateCount = marketingMapper.updateByPrimaryKeySelective(updateMarketing);
        if(updateCount>0){
            //判断marketingname是否是重复值
            ServerResponse validResponse = this.checkValid(marketing.getMarketingName());
            if(!validResponse.isSuccess()){
                //校验失败,将原marketing值写回,并且报告校验失败
                updateMarketing.setMarketingName(oldname);
                updateCount = marketingMapper.updateByPrimaryKeySelective(updateMarketing);
                if(updateCount > 0){
                    return validResponse;
                }
            }
            //校验成功,将新值写入
            updateMarketing.setMarketingName(marketing.getMarketingName());
            updateMarketing.setMarketingClass(marketing.getMarketingClass());
            updateMarketing.setStatus(marketing.getStatus());
            updateCount = marketingMapper.updateByPrimaryKeySelective(updateMarketing);
            if(updateCount > 0){
                return ServerResponse.createBySuccess("更新渠道信息成功",marketingMapper.selectByPrimaryKey(marketing.getId()));
            }
        }
        return ServerResponse.createByErrorMessage("更新渠道信息失败");
    }
}
