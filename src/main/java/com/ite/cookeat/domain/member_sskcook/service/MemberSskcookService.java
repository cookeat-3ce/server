package com.ite.cookeat.domain.member_sskcook.service;

import com.ite.cookeat.domain.member_sskcook.dto.PostMemberSskcookReq;

public interface MemberSskcookService {

  void addMemberSskcook(PostMemberSskcookReq postMemberSskcookReq);

  void removeMemberSskcook(PostMemberSskcookReq postMemberSskcookReq);

  Integer findMemberSskcook(PostMemberSskcookReq postMemberSskcookReq);
}
