package com.plc.controller.portal;

import com.plc.common.Const;
import com.plc.common.ResponseCode;
import com.plc.common.ServerResponse;
import com.plc.pojo.User;
import com.plc.service.IUserService;
import com.sun.corba.se.spi.activation.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by geely
 */
@Controller
@RequestMapping("/user/")
public class UserController {


    @Autowired
    private IUserService iUserService;



    @RequestMapping(value = "list_user.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> listUser(HttpSession session,Integer role){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }

        //判断是否为管理员,若不是管理员则取自己所在中心
        Integer centreCode;
        if(iUserService.checkAdminRole(currentUser).isSuccess()){
            //填充我们增加产品的业务逻辑
            centreCode =1; // TODO: 2018/4/4 需要填充用户是管理员时的逻辑
        }else{
            centreCode=currentUser.getCentre();
        }
        return iUserService.listUser(role,centreCode);
    }
    /**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session){
        ServerResponse<User> response = iUserService.login(username,password);
        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    @RequestMapping(value = "logout.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "register.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(HttpSession session,User user){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(!iUserService.checkAdminRole(currentUser).isSuccess()){
            return ServerResponse.createByErrorMessage("无权限操作");
        }
        return iUserService.register(user);
    }


    @RequestMapping(value = "check_valid.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkValid(String str,String type){
        return iUserService.checkValid(str,type);
    }


    @RequestMapping(value = "get_user_info.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user != null){
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
    }


    /*@RequestMapping(value = "forget_get_question.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username){
        return iUserService.selectQuestion(username);
    }


    @RequestMapping(value = "forget_check_answer.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String username,String question,String answer){
        return iUserService.checkAnswer(username,question,answer);
    }


    @RequestMapping(value = "forget_reset_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String username,String passwordNew,String forgetToken){
        return iUserService.forgetResetPassword(username,passwordNew,forgetToken);
    }*/



    @RequestMapping(value = "reset_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session,String passwordOld,String passwordNew){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iUserService.resetPassword(passwordOld,passwordNew,user);
    }

    @RequestMapping(value = "reset_password_admin.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPasswordAdmin(HttpSession session,String passwordNew,User user){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(!iUserService.checkAdminRole(currentUser).isSuccess()){
            return ServerResponse.createByErrorMessage("无权限操作");
        }
        return iUserService.resetPasswordByAdmin(passwordNew,user);
    }


    @RequestMapping(value = "update_information.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateInformation(HttpSession session,User user){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        ServerResponse<User> response = iUserService.updateInformation(user);
        if(response.isSuccess()){
            response.getData().setUsername(currentUser.getUsername());
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    @RequestMapping(value = "get_information.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getInformation(HttpSession session){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录,需要强制登录status=10");
        }
        return iUserService.getInformation(currentUser.getId());
    }

    @RequestMapping(value = "update_information_admin.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateInformationAdmin(HttpSession session,User user){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(!iUserService.checkAdminRole(currentUser).isSuccess()){
            return ServerResponse.createByErrorMessage("无权限操作");
        }

        ServerResponse<User> response = iUserService.updateInformation(user);
        if(!response.isSuccess()){
            return ServerResponse.createByErrorMessage("更新失败");
        }

        return response;
    }


    @RequestMapping(value = "get_information_admin.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getInformationAdmin(HttpSession session,@RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(!iUserService.checkAdminRole(currentUser).isSuccess()){
            return ServerResponse.createByErrorMessage("无权限操作");
        }


        return iUserService.getInformationList(pageNum,pageSize);
    }



























}
