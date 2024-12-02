package com.korit.fileupload_back.mapper;

import com.korit.fileupload_back.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    int insert(Member member);
}
