package com.ite.cookeat.domain.member.service;

import com.ite.cookeat.domain.member.dto.GetMemberNoticePageRes;
import com.ite.cookeat.domain.member.dto.GetUserDetailsRes;
import com.ite.cookeat.domain.member.dto.PostLoginReq;
import com.ite.cookeat.domain.member.dto.PostLoginRes;
import com.ite.cookeat.domain.member.dto.PostSignUpReq;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookReq;
import java.util.List;

public interface MemberService {

  GetUserDetailsRes findUserDetailsByUsername(String username);

  void addMember(PostSignUpReq postSignUpReq);

  PostLoginRes login(PostLoginReq postLoginReq);

  GetMemberNoticePageRes findMemberNotices(String username, Integer page);

  Integer findMemberId(String username);

  List<GetUserDetailsRes> findSearchMember(GetSearchSskcookReq getSearchSskcookReq);

  void modifyMemberDeletedate(String username);
}
