package com.plc.service;

import com.github.pagehelper.PageInfo;
import com.plc.common.ServerResponse;
import com.plc.pojo.User;

import java.util.List;

/**
 * Created by geely
 */
public interface IUserService {

    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str, String type);

//    ServerResponse selectQuestion(String username);

//    ServerResponse<String> checkAnswer(String username, String question, String answer);

//    ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken);

    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

    ServerResponse<User> updateInformation(User user);

    ServerResponse<User> getInformation(Integer userId);

    ServerResponse checkAdminRole(User user);

    ServerResponse<PageInfo> getInformationList(int pageNum, int pageSize);
    ServerResponse<String> resetPasswordByAdmin(String passwordNew,User user);
    ServerResponse listUser(Integer role, Integer centre);
}
