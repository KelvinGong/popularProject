package com.plc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plc.common.ServerResponse;
import com.plc.dao.CourseMapper;
import com.plc.pojo.Course;
import com.plc.service.ICourseService;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.LastModified;

import java.util.List;
import java.util.UUID;

/**
 * Created by gongkelvin on 2018/3/16.
 */
@Service("iCourseService")
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 用于显示课程列表,无status校验,有分页插件
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse<PageInfo> getCourseList(int pageNum, int pageSize){
        //startPage--start
        //填充自己的sql查询逻辑
        //pageHelper-收尾
        PageHelper.startPage(pageNum,pageSize);
        List<Course> courseList = courseMapper.selectList();

/*        List<ProductListVo> productListVoList = Lists.newArrayList();
        for(Product productItem : productList){
            ProductListVo productListVo = assembleProductListVo(productItem);
            productListVoList.add(productListVo);
        }*/
        PageInfo pageResult = new PageInfo(courseList);
        //pageResult.setList(productListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    /**
     * 用于显示渠道列表,有status校验,无分页插件
     * @return
     */
    public ServerResponse getActiveCourseList(){
        List<Course> courseList = courseMapper.selectActiveList();
        return ServerResponse.createBySuccess(courseList);
    }

    public ServerResponse<String> addCourse(Course course,Integer currentUserId){
        ServerResponse validResponse = this.checkValid(course.getCourseCode());
        if(!validResponse.isSuccess()){
            return validResponse;
        }
        course.setLastUpdateUser(currentUserId);
        int resultCount = courseMapper.insert(course);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("添加课程失败");
        }
        return ServerResponse.createBySuccessMessage("添加课程成功");
    }

    /**
     * 校验方法
     * @param str 渠道名字
     * @return
     */
    public ServerResponse<String> checkValid(String str){
        //开始校验
        int resultCount = courseMapper.checkCourseCode(str);
        if(resultCount > 0 ){
            return ServerResponse.createByErrorMessage("课程code已存在");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    public ServerResponse<Course> updateCourse(Course course, Integer currentUserId){
        //Centrename是不能被更新的
        //email也要进行一个校验,校验新的email是不是已经存在,并且存在的email如果相同的话,不能是我们当前的这个用户的.
        Course tempCourse= courseMapper.selectByPrimaryKey(course.getId());
        if(tempCourse==null){
            return ServerResponse.createByErrorMessage("参数错误(id未匹配)");
        }
        String oldname=tempCourse.getCourseCode();
        //把当前id的渠道名修改为不可重复值
        Course updateCourse = new Course();
        updateCourse.setId(course.getId());
        if(course.getCourseCode()!=null){
            updateCourse.setCourseCode(UUID.randomUUID().toString());

            int updateCount = courseMapper.updateByPrimaryKeySelective(updateCourse);
            if(updateCount>0){
                //判断coursename是否是重复值
                ServerResponse validResponse = this.checkValid(course.getCourseCode());
                if(!validResponse.isSuccess()){
                    //校验失败,将原course值写回,并且报告校验失败
                    updateCourse.setCourseCode(oldname);
                    updateCount = courseMapper.updateByPrimaryKeySelective(updateCourse);
                    if(updateCount > 0){
                        return validResponse;
                    }
                }
            }
            updateCourse.setCourseCode(course.getCourseCode());
        }
        //校验成功,将新值写入

        updateCourse.setCourseDesc(course.getCourseDesc());
        updateCourse.setPrice(course.getPrice());
        updateCourse.setStatus(course.getStatus());
        updateCourse.setIsShort(course.getIsShort());
        updateCourse.setRemarks(course.getRemarks());

        int updateCount = courseMapper.updateByPrimaryKeySelective(updateCourse);
        if(updateCount >0 && this.lastUpdateUser(course.getId(), currentUserId)){
            return ServerResponse.createBySuccess("更新课程信息成功",courseMapper.selectByPrimaryKey(course.getId()));
        }
        return ServerResponse.createByErrorMessage("更新课程信息失败");
    }

    private boolean lastUpdateUser(int id, int lastUpdateUser){
        int updateCount = courseMapper.updateLastUser(id ,lastUpdateUser);
        if(updateCount>0){
            return true;
        }
        return false;
    }
}
