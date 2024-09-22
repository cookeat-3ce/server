package com.ite.cookeat.domain.member.service;

import com.ite.cookeat.domain.member.dto.GetMemberNoticeRes;
import com.ite.cookeat.domain.member.dto.GetSubscriptionMemberDetailsRes;
import com.ite.cookeat.domain.member.dto.GetUserDetailsRes;
import com.ite.cookeat.domain.member.dto.PostLoginReq;
import com.ite.cookeat.domain.member.dto.PostLoginRes;
import com.ite.cookeat.domain.member.dto.PostMemberOneLinerReq;
import com.ite.cookeat.domain.member.dto.PostSignUpReq;
import com.ite.cookeat.domain.member.dto.PostSubscriptionReq;
import com.ite.cookeat.global.dto.PaginatedRes;

/**
 * 멤버와 관련된 요청을 처리하는 Service
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
 * 2024.08.23    김지수       사용자 공지 목록 조회
 * 2024.08.24    김지수       사용자 한줄 소개 등록
 * 2024.08.26    김지수       사용자 인증 상태 조회
 * 2024.08.27    김지수       사용자 구독
 * 2024.08.29    김지수       구독, 슥쿡 조회 stored procedure 적용
 * </pre>
 */
public interface MemberService {

  // 멤버 정보 조회
  GetUserDetailsRes findUserDetailsByUsername(String username);

  // 회원 가입
  void addMember(PostSignUpReq postSignUpReq);

  // 로그인
  PostLoginRes login(PostLoginReq postLoginReq);

  // 사용자 공지 조회
  PaginatedRes<GetMemberNoticeRes> findMemberNotices(String username, Integer page);

  // username으로 member ID 조회
  Integer findMemberId(String username);

  PaginatedRes<GetUserDetailsRes> findSearchMember(String keyword, Integer page);

  // 사용자 한줄 소개
  Integer modifyMemberOneLiner(PostMemberOneLinerReq req);

  // 사용자 구독 목록
  PaginatedRes<GetSubscriptionMemberDetailsRes> findMemberSubscriptionList(Integer page);

  // 사용자 탈퇴
  void modifyMemberDeletedate(String username);

  // 사용자 인증 상태 업데이트
  Integer modifyVerifyStatus(String username, String status);

  // 사용자 인증 상태 조회
  String findMemberVerifiedStatus(String username);

  // 사용자 구독
  Integer addSubscription(PostSubscriptionReq req);

}
