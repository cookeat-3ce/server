package com.ite.cookeat.domain.member.mapper;

import com.ite.cookeat.domain.member.dto.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberMapper {
    Optional<Member> selectUsername(String username);
    void insertMember(Member member);
    Integer selectDuplicatedUsername(String username);
}
