package com.plc.controller.portal;

import com.plc.common.Const;
import com.plc.common.ResponseCode;
import com.plc.common.ServerResponse;
import com.plc.pojo.Marketing;
import com.plc.pojo.User;
import com.plc.service.IMarketingService;
import com.plc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by gongkelvin on 2018/3/15.
 */
@Controller
@RequestMapping("/marketing/")
public class MarketingController {

    @Autowired
    private IMarketingService iMarketingService;

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "list_marketing.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse listMarketing(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
/*      ServerResponse<User> response = iUserService.login(username,password);

        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }*/

        return iMarketingService.getMarketingList(pageNum, pageSize);
    }

    @RequestMapping(value = "list_active_Marketing.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse listActiveMarketing(){
/*        ServerResponse<User> response = iUserService.login(username,password);

        if(response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }*/

        return iMarketingService.getActiveMarketingList();
    }

    @RequestMapping(value = "add_marketing.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse addMarketing(HttpSession session, Marketing marketing){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //填充我们增加产品的业务逻辑
            return iMarketingService.addMarketing(marketing);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping(value = "update_marketing.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse updateMarketing(HttpSession session,Marketing marketing){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //填充我们增加产品的业务逻辑
            return iMarketingService.updateMarketing(marketing);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作");
        }

    }
}
