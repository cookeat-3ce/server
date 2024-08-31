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

public interface MemberService {

  GetUserDetailsRes findUserDetailsByUsername(String username);

  void addMember(PostSignUpReq postSignUpReq);

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
