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
 * 2024.08.23    김지수       공지 목록 조회
 * 2024.08.24    김지수       한줄 소개 등록
 * 2024.08.26    김지수       사용자 인증 상태 조회
 * 2024.08.27    김지수       사용자 구독
 * 2024.08.29    김지수       구독 목록 stored procedure 적용
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

  // 사용자 공지 목록 조회 (페이징)
  List<GetMemberNoticeRes> selectMemberNotices(@Param("cri") Criteria cri,
      @Param("username") String username);

  // 사용자 공지 전체 갯수 조회
  Integer selectMemberNoticeCount(String username);

  // username으로 member ID 조회
  Optional<Integer> selectMemberId(String username);

  // 멤버 검색 페이징 조회
  List<GetUserDetailsRes> selectSearchMember(@Param("cri") Criteria cri,
      @Param("keyword") String keyword);

  // 멤버 검색 페이징 카운트
  Integer selectSearchMemberCount(String keyword);

  // 사용자 한줄 소개 수정
  Integer updateMemberOneLiner(PostMemberOneLinerReq req);

  // 사용자 구둑 목록 조회
  void selectMemberSubscriptionList(GetSubscriptionMemberReq req);

  // 사용자 탈퇴
  Integer updateMemberDeletedate(String username);

  // 사용자 인증 상태 변경 (기존 -> status)
  Integer updateVerifiedStatus(@Param("username") String username, @Param("status") String status);

  // 사용자 인증 상태 조회
  String selectMemberVerifiedStatus(String username);

  // 사용자 구독
  Integer insertSubscription(PostSubscriptionReq req);
}
