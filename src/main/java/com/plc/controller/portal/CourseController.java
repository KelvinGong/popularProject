package com.plc.controller.portal;

import com.plc.common.Const;
import com.plc.common.ResponseCode;
import com.plc.common.ServerResponse;
import com.plc.pojo.Course;
import com.plc.pojo.User;
import com.plc.service.ICourseService;
import com.plc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by gongkelvin on 2018/3/16.
 */
@Controller
@RequestMapping("/course/")
public class CourseController {

    @Autowired
    private ICourseService iCourseService;

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "list_course.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse listCourse(
                                     @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){

        return iCourseService.getCourseList(pageNum, pageSize);
    }

    @RequestMapping(value = "list_active_course.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse listActiveCourse(){
/*        ServerResponse<User> response = iUserService.login(username,password);

        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }*/

        return iCourseService.getActiveCourseList();
    }

    @RequestMapping(value = "add_course.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse addCourse(HttpSession session, Course course){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //填充我们增加产品的业务逻辑
            return iCourseService.addCourse(course,user.getId());
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping(value = "update_course.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse updateCourse(HttpSession session,Course course){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //填充我们增加产品的业务逻辑
            return iCourseService.updateCourse(course,user.getId());
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }

    }
}
