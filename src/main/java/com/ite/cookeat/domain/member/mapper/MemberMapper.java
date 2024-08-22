package com.ite.cookeat.domain.member.mapper;

import com.ite.cookeat.domain.member.dto.GetUserDetailsRes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    GetUserDetailsRes selectUserDetails(String username);
}
