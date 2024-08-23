package com.ite.cookeat.domain.member.mapper;

import com.ite.cookeat.domain.member.dto.GetUserDetailsRes;
import org.apache.ibatis.annotations.Mapper;
import com.ite.cookeat.domain.member.dto.Member;
import java.util.Optional;


@Mapper
public interface MemberMapper {

  GetUserDetailsRes selectUserDetails(String username);
  Optional<Member> selectUsername(String username);
  void insertMember(Member member);
  Integer selectDuplicatedUsername(String username);
  Optional<Integer> selectMemberId(String username);
}
