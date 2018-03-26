package com.plc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plc.common.ServerResponse;
import com.plc.dao.ClassMapper;
import com.plc.pojo.Class;
import com.plc.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by gongkelvin on 2018/3/19.
 */
@Service("iClassService")
public class ClassServiceImpl implements IClassService {

    @Autowired
    private ClassMapper classMapper;

    /**
     * 用于显示课程列表,无status校验,有分页插件
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse<PageInfo> getClassList(int pageNum, int pageSize){
        //startPage--start
        //填充自己的sql查询逻辑
        //pageHelper-收尾
        PageHelper.startPage(pageNum,pageSize);
        List<Class> classList = classMapper.selectList();

/*        List<ProductListVo> productListVoList = Lists.newArrayList();
        for(Product productItem : productList){
            ProductListVo productListVo = assembleProductListVo(productItem);
            productListVoList.add(productListVo);
        }*/
        PageInfo pageResult = new PageInfo(classList);
        //pageResult.setList(productListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }
    public ServerResponse<PageInfo> getClassList(int pageNum, int pageSize,Integer centreCode){
        //startPage--start
        //填充自己的sql查询逻辑
        //pageHelper-收尾
        PageHelper.startPage(pageNum,pageSize);
        List<Class> classList = classMapper.selectList(centreCode);

/*        List<ProductListVo> productListVoList = Lists.newArrayList();
        for(Product productItem : productList){
            ProductListVo productListVo = assembleProductListVo(productItem);
            productListVoList.add(productListVo);
        }*/
        PageInfo pageResult = new PageInfo(classList);
        //pageResult.setList(productListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    /**
     * 用于显示渠道列表,有status校验,无分页插件
     * @return
     */
    public ServerResponse getActiveClassList(Integer centreCode){
        List<Class> classList = classMapper.selectActiveList(centreCode);
        return ServerResponse.createBySuccess(classList);
    }

    public ServerResponse<String> addClass(Class classOB,Integer currentUserId){
        ServerResponse validResponse = this.checkValid(classOB.getClassCode());
        if(!validResponse.isSuccess()){
            return validResponse;
        }
        classOB.setLastUpdateUser(currentUserId);

        int resultCount = classMapper.insert(classOB);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("添加班级失败");
        }
        return ServerResponse.createBySuccessMessage("添加班级成功");
    }

    /**
     * 校验方法
     * @param str 渠道名字
     * @return
     */
    public ServerResponse<String> checkValid(String str){
        //开始校验
        int resultCount = classMapper.checkClassCode(str);
        if(resultCount > 0 ){
            return ServerResponse.createByErrorMessage("班级号已存在");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    public ServerResponse<Class> updateClass(Class classOB, Integer currentUserId){
        //Centrename是不能被更新的
        //email也要进行一个校验,校验新的email是不是已经存在,并且存在的email如果相同的话,不能是我们当前的这个用户的.
        Class tempClass= classMapper.selectByPrimaryKey(classOB.getId());
        if(tempClass==null){
            return ServerResponse.createByErrorMessage("参数错误(id未匹配)");
        }
        String oldname=tempClass.getClassCode();
        //把当前id的渠道名修改为不可重复值
        Class updateClass = new Class();
        updateClass.setId(classOB.getId());
        if(classOB.getClassCode()!=null){
            updateClass.setClassCode(UUID.randomUUID().toString());

            int updateCount = classMapper.updateByPrimaryKeySelective(updateClass);
            if(updateCount>0){
                //判断coursename是否是重复值
                ServerResponse validResponse = this.checkValid(classOB.getClassCode());
                if(!validResponse.isSuccess()){
                    //校验失败,将原course值写回,并且报告校验失败
                    updateClass.setClassCode(oldname);
                    updateCount = classMapper.updateByPrimaryKeySelective(updateClass);
                    if(updateCount > 0){
                        return validResponse;
                    }
                }
            }
            updateClass.setClassCode(classOB.getClassCode());
        }
        //校验成功,将新值写入

        updateClass.setCtrCode(classOB.getCtrCode());
        updateClass.setCourseCode(classOB.getCourseCode());
        updateClass.setStatus(classOB.getStatus());
        updateClass.setFirstclassTime(classOB.getFirstclassTime());
        updateClass.setEndclassTime(classOB.getEndclassTime());
        updateClass.setDayOfWeek(classOB.getDayOfWeek());
        updateClass.setEi(classOB.getEi());
        updateClass.setTa(classOB.getTa());
        updateClass.setIi(classOB.getIi());
        updateClass.setRemarks(classOB.getRemarks());

        int updateCount = classMapper.updateByPrimaryKeySelective(updateClass);
        if(updateCount >0 && this.lastUpdateUser(classOB.getId(), currentUserId)){
            return ServerResponse.createBySuccess("更新班级信息成功",classMapper.selectByPrimaryKey(classOB.getId()));
        }
        return ServerResponse.createByErrorMessage("更新班级信息失败");
    }

    private boolean lastUpdateUser(int id, int lastUpdateUser){
        int updateCount = classMapper.updateLastUser(id ,lastUpdateUser);
        if(updateCount>0){
            return true;
        }
        return false;
    }
}
