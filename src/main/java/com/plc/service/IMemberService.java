package com.plc.service;

import com.github.pagehelper.PageInfo;
import com.plc.common.ServerResponse;
import com.plc.pojo.Member;
import com.plc.vo.MemberVo;

import java.util.Date;

/**
 * Created by gongkelvin on 2018/3/15.
 */
public interface IMemberService {

    ServerResponse<String> addMember(Member member);

    ServerResponse<Member> updateMember(Member member);

    ServerResponse<PageInfo> listMember(Integer centreCode,
                                        String keyword,
                                        String field,
                                        int pageNum,
                                        int pageSize,
                                        String orderByField,
                                        String orderBy
    );

    ServerResponse<MemberVo> selectById(Integer id);
}
