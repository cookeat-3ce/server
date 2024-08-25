package com.ite.cookeat.domain.member_sskcook.service;

import com.ite.cookeat.domain.member_sskcook.dto.GetMemberSskcookPageRes;

public interface MemberSskcookService {

  void addMemberSskcook(String username, Integer sskcookId);

  void removeMemberSskcook(String username, Integer sskcookId);

  Integer findMemberSskcook(String username, Integer sskcookId);

  GetMemberSskcookPageRes findMemberSskcookList(String username, Integer page);
}
