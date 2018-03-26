package com.plc.dao;

import com.plc.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUsername(String username);

    User selectLogin(@Param("username") String username, @Param("password") String password);

    int checkEmail(String email);

    int checkPassword(@Param(value = "password") String password, @Param("userId") Integer userId);

    List<User> selectList();

    List<User> selectRoleOfCtr(@Param("role") Integer role, @Param("centre")Integer centre);
}