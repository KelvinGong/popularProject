package com.plc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.plc.common.Const;
import com.plc.common.ServerResponse;
import com.plc.dao.CentreMapper;
import com.plc.pojo.Centre;
import com.plc.service.ICentreService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gongkelvin on 2018/3/14.
 */
@Service("iCetnreService")
public class CentreServiceImpl implements ICentreService{

    @Autowired
    private CentreMapper centreMapper;

    /**
     * 用于显示中心列表页面,无status校验
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse<PageInfo> getCentreList(int pageNum, int pageSize){
        //startPage--start
        //填充自己的sql查询逻辑
        //pageHelper-收尾
        PageHelper.startPage(pageNum,pageSize);
        List<Centre> centreList = centreMapper.selectList();

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
     * 用于页面中类似下拉菜单显示,有status校验
     * @return
     */
    public ServerResponse getActiveCentreList(){
        List<Centre> centreList = centreMapper.selectActiveList();
        return ServerResponse.createBySuccess(centreList);
    }

    public ServerResponse selectById(Integer id){
        Centre centre = centreMapper.selectByPrimaryKey(id);
        if(centre!= null){
            return ServerResponse.createBySuccess(centre);
        }
        return ServerResponse.createByErrorMessage("未找到");
    }

    public ServerResponse<String> newCentre(Centre centre){
        ServerResponse validResponse = this.checkValid(centre.getCentreCode());
        if(!validResponse.isSuccess()){
            return validResponse;
        }

        //user.setRole(Const.Role.ROLE_CUSTOMER);
        //user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int resultCount = centreMapper.insert(centre);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("添加中心失败");
        }
        return ServerResponse.createBySuccessMessage("添加中心成功");
    }


    public ServerResponse<String> checkValid(String str){

            //开始校验

        int resultCount = centreMapper.checkCtrCode(str);
        if(resultCount > 0 ){
            return ServerResponse.createByErrorMessage("中心代码已存在");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    public ServerResponse<Centre> updateCentre(Centre centre){
        //Centrename是不能被更新的
        //email也要进行一个校验,校验新的email是不是已经存在,并且存在的email如果相同的话,不能是我们当前的这个用户的.

        Centre updateCentre = new Centre();
        updateCentre.setId(centre.getId());
        updateCentre.setCentreName(centre.getCentreName());
        updateCentre.setCentreRegion(centre.getCentreRegion());
        updateCentre.setAddress(centre.getAddress());
        updateCentre.setStatus(centre.getStatus());


        int updateCount = centreMapper.updateByPrimaryKeySelective(updateCentre);
        if(updateCount > 0){
            return ServerResponse.createBySuccess("更新中心信息成功",centreMapper.selectByPrimaryKey(centre.getId()));
        }
        return ServerResponse.createByErrorMessage("更新中心信息失败");
    }

    public String getCentreName(Integer id){
        Centre selectCount=centreMapper.selectByPrimaryKey(id);
        if(selectCount!=null){
            return selectCount.getCentreCode();
        }
        return "not found";
    }

}
