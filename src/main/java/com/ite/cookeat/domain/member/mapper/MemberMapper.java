package com.ite.cookeat.domain.member.mapper;

import com.ite.cookeat.domain.member.dto.GetMemberNoticeRes;
import com.ite.cookeat.domain.member.dto.GetSubscriptionMemberReq;
import com.ite.cookeat.domain.member.dto.GetUserDetailsReq;
import com.ite.cookeat.domain.member.dto.GetUserDetailsRes;
import com.ite.cookeat.domain.member.dto.Member;
import com.ite.cookeat.domain.member.dto.PostMemberOneLinerReq;
import com.ite.cookeat.domain.member.dto.PostSubscriptionReq;
import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;

/**
 * 멤버와 관련된 요청을 처리하는 Mybatis Mapper
 *
 * @author 양재혁
 * @version 1.0
 * @since 2024.08.22
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.22    양재혁       최초 생성
 * </pre>
 */

public interface MemberMapper {

  // 멤버 정보 조회
  void selectUserDetails(GetUserDetailsReq req);

  // 비회원 멤버 정보 조회
  void selectNullUserDetails(GetUserDetailsReq req);

  // 멤버 네임 조회
  Optional<Member> selectUsername(String username);

  // 회원 가입
  void insertMember(Member member);

  // 멤버 네임 중복 조회
  Integer selectDuplicatedUsername(String username);

  List<GetMemberNoticeRes> selectMemberNotices(@Param("cri") Criteria cri,
      @Param("username") String username);

  Integer selectMemberNoticeCount(String username);

  Optional<Integer> selectMemberId(String username);

  // 멤버 검색 페이징 조회
  List<GetUserDetailsRes> selectSearchMember(@Param("cri") Criteria cri,
      @Param("keyword") String keyword);

  // 멤버 검색 페이징 카운트
  Integer selectSearchMemberCount(String keyword);

  Integer updateMemberOneLiner(PostMemberOneLinerReq req);

  void selectMemberSubscriptionList(GetSubscriptionMemberReq req);

  Integer updateMemberDeletedate(String username);

  Integer updateVerifiedStatus(@Param("username") String username, @Param("status") String status);

  String selectMemberVerifiedStatus(String username);

  Integer insertSubscription(PostSubscriptionReq req);
}
