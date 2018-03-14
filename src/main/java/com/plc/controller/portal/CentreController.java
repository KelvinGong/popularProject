package com.plc.controller.portal;

import com.plc.common.Const;
import com.plc.common.ResponseCode;
import com.plc.common.ServerResponse;
import com.plc.pojo.Centre;
import com.plc.pojo.User;
import com.plc.service.ICentreService;
import com.plc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by gongkelvin on 2018/3/14.
 */
@Controller
@RequestMapping("/centre/")
public class CentreController {

    @Autowired
    private ICentreService iCentreService;

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "list_centre.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse listCentre(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
/*        ServerResponse<User> response = iUserService.login(username,password);

        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }*/

        return iCentreService.getCentreList(pageNum, pageSize);
    }

    @RequestMapping(value = "list_active_centre.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse listActiveCentre(){
/*        ServerResponse<User> response = iUserService.login(username,password);

        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }*/

        return iCentreService.getActiveCentreList();
    }

    @RequestMapping(value = "new_centre.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse newCentre(HttpSession session,Centre centre){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //填充我们增加产品的业务逻辑
            return iCentreService.newCentre(centre);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping(value = "update_centre.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse updateCentre(HttpSession session,Centre centre){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //填充我们增加产品的业务逻辑
            return iCentreService.updateCentre(centre);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }

    }






}
