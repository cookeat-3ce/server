package com.ite.cookeat.domain.member.mapper;

import com.ite.cookeat.domain.member.dto.GetMemberNoticeRes;
import com.ite.cookeat.domain.member.dto.GetSubscriptionUserDetailsRes;
import com.ite.cookeat.domain.member.dto.GetUserDetailsRes;
import com.ite.cookeat.domain.member.dto.Member;
import com.ite.cookeat.domain.member.dto.PostMemberOneLinerReq;
import com.ite.cookeat.domain.member.dto.PostSubscriptionReq;
import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;


public interface MemberMapper {


  GetUserDetailsRes selectUserDetails(String username);

  Optional<Member> selectUsername(String username);

  void insertMember(Member member);

  Integer selectDuplicatedUsername(String username);

  List<GetMemberNoticeRes> selectMemberNotices(@Param("cri") Criteria cri,
      @Param("username") String username);

  Integer selectMemberNoticeCount(String username);

  Optional<Integer> selectMemberId(String username);

  List<GetUserDetailsRes> selectSearchMember(@Param("cri") Criteria cri,
      @Param("keyword") String keyword);

  Integer selectSearchMemberCount(String keyword);

  Integer updateMemberOneLiner(PostMemberOneLinerReq req);


  List<GetSubscriptionUserDetailsRes> selectMemberSubscriptionList(@Param("cri") Criteria cri,
      @Param("username") String username);

  Integer selectMemberSubscriptionListCount(String username);

  Integer updateMemberDeletedate(String username);

  Integer updateVerifiedStatus(@Param("username") String username, @Param("status") String status);

  String selectMemberVerifiedStatus(String username);

  Integer insertSubscription(PostSubscriptionReq req);
}
