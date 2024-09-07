package com.ite.cookeat.domain.member_sskcook.service;

import com.ite.cookeat.domain.member_sskcook.dto.GetMemberSskcookRes;
import com.ite.cookeat.domain.member_sskcook.dto.PostMemberSskcookReq;
import com.ite.cookeat.global.dto.PaginatedRes;

public interface MemberSskcookService {

  void addMemberSskcook(PostMemberSskcookReq req);

  void removeMemberSskcook(PostMemberSskcookReq req);

  Integer findMemberSskcook(PostMemberSskcookReq req);

  PaginatedRes<GetMemberSskcookRes> findMemberSskcookList(Integer page);
}
