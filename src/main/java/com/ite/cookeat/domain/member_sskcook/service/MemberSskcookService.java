package com.ite.cookeat.domain.member_sskcook.service;

import com.ite.cookeat.domain.member_sskcook.dto.GetMemberSskcookRes;
import com.ite.cookeat.global.dto.PaginatedRes;

public interface MemberSskcookService {

  void addMemberSskcook(String username, Integer sskcookId);

  void removeMemberSskcook(String username, Integer sskcookId);

  Integer findMemberSskcook(String username, Integer sskcookId);

  PaginatedRes<GetMemberSskcookRes> findMemberSskcookList(String username, Integer page);
}
