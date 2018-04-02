package com.plc.controller.portal;

import com.plc.common.Const;
import com.plc.common.ResponseCode;
import com.plc.common.ServerResponse;
import com.plc.pojo.Member;
import com.plc.pojo.User;
import com.plc.service.IDataAnaService;
import com.plc.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by gongkelvin on 2018/3/27.
 */
@Controller
@RequestMapping("/report/")
public class ReportController {
    @Autowired
    private IDataAnaService iDataAnaService;
    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "cc_report01.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse ccReport01(HttpSession session,
                                     @RequestParam(value = "dateField",required=false) String dateField,
                                     @RequestParam(value = "startDate",required=false) String startDate,
                                     @RequestParam(value = "endDate",required=false) String endDate,
                                     @RequestParam(value = "centreCode",required = false) Integer centreCode){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }

        if(!iUserService.checkAdminRole(currentUser).isSuccess()){
            return iDataAnaService.ccAna01(currentUser.getCentre(),startDate, endDate, dateField);
        }
        return iDataAnaService.ccAna01(centreCode,startDate,endDate, dateField);

    }

}
