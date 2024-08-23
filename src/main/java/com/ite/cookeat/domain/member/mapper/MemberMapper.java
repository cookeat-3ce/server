package com.ite.cookeat.domain.member.mapper;

import com.ite.cookeat.domain.member.dto.GetMemberNoticeRes;
import com.ite.cookeat.domain.member.dto.GetUserDetailsRes;
import com.ite.cookeat.domain.member.dto.Member;
import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MemberMapper {

  GetUserDetailsRes selectUserDetails(String username);

  Optional<Member> selectUsername(String username);

  void insertMember(Member member);

  Integer selectDuplicatedUsername(String username);

  List<GetMemberNoticeRes> selectMemberNotices(@Param("cri") Criteria cri,
      @Param("username") String username);

  Integer selectMemberNoticeCount(String username);
}
