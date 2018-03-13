package com.plc.dao;

import com.plc.pojo.Deposit;

public interface DepositMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Deposit record);

    int insertSelective(Deposit record);

    Deposit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Deposit record);

    int updateByPrimaryKey(Deposit record);
}