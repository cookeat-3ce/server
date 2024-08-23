package com.ite.cookeat.domain.member.mapper;

import com.ite.cookeat.domain.member.dto.GetUserDetailsRes;
import com.ite.cookeat.domain.member.dto.Member;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookReq;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MemberMapper {

  GetUserDetailsRes selectUserDetails(String username);

  Optional<Member> selectUsername(String username);

  void insertMember(Member member);

  Integer selectDuplicatedUsername(String username);

  Optional<Integer> selectMemberId(String username);

  List<GetUserDetailsRes> selectSearchMember(GetSearchSskcookReq getSearchSskcookReq);
}
