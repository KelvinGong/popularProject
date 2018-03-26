package com.plc.controller.portal;

import com.plc.common.Const;
import com.plc.common.ResponseCode;
import com.plc.common.ServerResponse;
import com.plc.pojo.Class;
import com.plc.pojo.User;
import com.plc.service.IClassService;
import com.plc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by gongkelvin on 2018/3/19.
 */
@Controller
@RequestMapping("/class/")
public class ClassControl {

    @Autowired
    private IClassService iClassService;

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "list_class.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse listClass(HttpSession session,
                                    @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                                    @RequestParam(value = "ctrCode",required = false)Integer ctrCode){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            return iClassService.getClassList(pageNum, pageSize);
        }else{
            ctrCode=user.getCentre();
            return iClassService.getClassList(pageNum, pageSize,ctrCode);
        }

    }

    @RequestMapping(value = "list_active_class.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse listActiveClass(HttpSession session){
/*        ServerResponse<User> response = iUserService.login(username,password);

        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }*/
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            return ServerResponse.createByErrorMessage("管理员用户需指定查看中心");
        }else{
            Integer ctrCode=user.getCentre();
            return iClassService.getActiveClassList(ctrCode);
        }

    }

    @RequestMapping(value = "add_class.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse addClass(HttpSession session, Class classOb){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        classOb.setCtrCode(user.getCentre());
        int centreCode=user.getCentre();
        return iClassService.addClass(classOb,centreCode);
    }

    @RequestMapping(value = "update_class.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse updateClass(HttpSession session,Class classOb){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        classOb.setCtrCode(user.getCentre());
        int centreCode=user.getCentre();
        return iClassService.updateClass(classOb,centreCode);
    }

}
