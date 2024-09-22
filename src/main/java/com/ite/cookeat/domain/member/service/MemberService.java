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
 * </pre>
 */

public interface MemberService {

  // 멤버 정보 조회
  GetUserDetailsRes findUserDetailsByUsername(String username);

  // 회원 가입
  void addMember(PostSignUpReq postSignUpReq);

  // 로그인
  PostLoginRes login(PostLoginReq postLoginReq);

  PaginatedRes<GetMemberNoticeRes> findMemberNotices(String username, Integer page);

  Integer findMemberId(String username);

  PaginatedRes<GetUserDetailsRes> findSearchMember(String keyword, Integer page);

  Integer modifyMemberOneLiner(PostMemberOneLinerReq req);

  PaginatedRes<GetSubscriptionMemberDetailsRes> findMemberSubscriptionList(Integer page);

  void modifyMemberDeletedate(String username);

  Integer modifyVerifyStatus(String username, String status);

  String findMemberVerifiedStatus(String username);

  Integer addSubscription(PostSubscriptionReq req);

}
