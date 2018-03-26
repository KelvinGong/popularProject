package com.plc.controller.portal;

import com.plc.common.Const;
import com.plc.common.ResponseCode;
import com.plc.common.ServerResponse;
import com.plc.pojo.Sell;
import com.plc.pojo.User;
import com.plc.service.ISellService;
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
@RequestMapping("/sell/")
public class SellController {

    @Autowired
    private ISellService iSellService;

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "list_sell.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse listSell(HttpSession session, @RequestParam(value = "keyword",required = false)String keyword,
                                     @RequestParam(value = "field",required = false)String field,
                                     @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                     @RequestParam(value = "orderByField",defaultValue = "") String orderByField,
                                     @RequestParam(value = "orderBy",defaultValue = "") String orderBy,
                                     @RequestParam(value = "centreCode",required = false) Integer centreCode){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        if(iUserService.checkAdminRole(currentUser).isSuccess()){
            //填充我们增加产品的业务逻辑
            centreCode = centreCode==null?0:centreCode;

        }else{
            centreCode=currentUser.getCentre();
        }

        return iSellService.listSell(centreCode,keyword, field, pageNum, pageSize, orderByField, orderBy);
    }

    @RequestMapping(value = "add_sell.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse addSell(HttpSession session, Sell sell){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if(!iUserService.checkAdminRole(currentUser).isSuccess()){
            sell.setCtrCode(currentUser.getCentre());
        }
        return iSellService.addSell(sell);

    }

    @RequestMapping(value = "update_sell.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse updateSell(HttpSession session, Sell sell){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if(!iUserService.checkAdminRole(currentUser).isSuccess()){
            sell.setCtrCode(currentUser.getCentre());
        }
        return iSellService.updateMember(sell);

    }
}
