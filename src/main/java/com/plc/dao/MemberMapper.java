package com.plc.dao;

import com.plc.pojo.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    List<Member> selectListAll();

    int checkMemberCode(String memberCode);

    List<Member> selectByKeyword(@Param("centreCode") int centreCode, @Param("keyword") String keyword, @Param("field")String field);
}