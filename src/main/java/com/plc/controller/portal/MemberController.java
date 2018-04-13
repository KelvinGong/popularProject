package com.plc.controller.portal;

import com.plc.common.Const;
import com.plc.common.ResponseCode;
import com.plc.common.ServerResponse;
import com.plc.pojo.Member;
import com.plc.pojo.User;
import com.plc.service.IMemberService;
import com.plc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by gongkelvin on 2018/3/15.
 */
@Controller
@RequestMapping("/member/")
public class MemberController {

    @Autowired
    private IMemberService iMemberService;

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "list_member.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse listMember(HttpSession session,@RequestParam(value = "keyword",required = false)String keyword,
                                     @RequestParam(value = "field",required = false)String field,
                                     @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                     @RequestParam(value = "orderByField",defaultValue = "") String orderByField,
                                     @RequestParam(value = "orderBy",defaultValue = "") String orderBy,
                                     @RequestParam(value = "centreCode",required = false) Integer centreCode
    ){
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

        return iMemberService.listMember(centreCode,keyword, field, pageNum, pageSize, orderByField, orderBy);
    }

    @RequestMapping(value = "list_member_ByCtr.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse listMemberByCtr(HttpSession session, @RequestParam(value = "centreCode",required = false) Integer centreCode){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        if(iUserService.checkAdminRole(currentUser).isSuccess()){
            //填充我们增加产品的业务逻辑
            centreCode = centreCode==null?1:centreCode;

        }else{
            centreCode=currentUser.getCentre();
        }

        return iMemberService.listMemberByCentre(centreCode);
    }

    @RequestMapping(value = "select_member.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse listMember(HttpSession session,@RequestParam(value = "id",required = false)Integer id){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iMemberService.selectById(id);
    }


    @RequestMapping(value = "add_member.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse addMember(HttpSession session, Member member){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if(!iUserService.checkAdminRole(currentUser).isSuccess()){
            member.setCentre(currentUser.getCentre());
        }
        return iMemberService.addMember(member);

    }

    @RequestMapping(value = "update_member.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse updateMember(HttpSession session, Member member){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        if(!iUserService.checkAdminRole(currentUser).isSuccess()){
            member.setCentre(currentUser.getCentre());
        }
        return iMemberService.updateMember(member);

    }

}
