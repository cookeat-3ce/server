package com.ite.cookeat.domain.member_sskcook.service;

import com.ite.cookeat.domain.member.service.MemberService;
import com.ite.cookeat.domain.member_sskcook.dto.GetMemberSskcookPageRes;
import com.ite.cookeat.domain.member_sskcook.dto.PostMemberSskcookReq;
import com.ite.cookeat.domain.member_sskcook.mapper.MemberSskcookMapper;
import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.exception.ErrorCode;
import com.ite.cookeat.global.dto.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSskcookServiceImpl implements MemberSskcookService {

  private final MemberSskcookMapper memberSskcookMapper;
  private final MemberService memberService;


  @Override
  public void addMemberSskcook(String username, Integer sskcookId) {
    PostMemberSskcookReq modifiedReq = PostMemberSskcookReq.builder()
        .memberId(memberService.findMemberId(username))
        .sskcookId(sskcookId)
        .build();
    int cnt = memberSskcookMapper.insertMemberSskcook(modifiedReq);
    if (cnt == 0) {
      throw new CustomException(ErrorCode.MEMBER_SSKCOOK_INSERT_FAIL);
    }
  }

  @Override
  public void removeMemberSskcook(String username, Integer sskcookId) {
    PostMemberSskcookReq modifiedReq = PostMemberSskcookReq.builder()
        .memberId(memberService.findMemberId(username))
        .sskcookId(sskcookId)
        .build();
    int cnt = memberSskcookMapper.deleteMemberSskcook(modifiedReq);
    if (cnt == 0) {
      throw new CustomException(ErrorCode.MEMBER_SSKCOOK_DELETE_FAIL);
    }

  }

  @Override
  public Integer findMemberSskcook(String username, Integer sskcookId) {
    PostMemberSskcookReq modifiedReq = PostMemberSskcookReq.builder()
        .memberId(memberService.findMemberId(username))
        .sskcookId(sskcookId)
        .build();
    return memberSskcookMapper.selectMemberSskcookCount(modifiedReq);
  }

  @Override
  public GetMemberSskcookPageRes findMemberSskcookList(String username, Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .build();

    return GetMemberSskcookPageRes.builder()
        .cri(cri)
        .total(memberSskcookMapper.selectMemberSskcookListCount(username))
        .memberSskcooks(memberSskcookMapper.selectMemberSskcookList(cri, username))
        .build();
  }
}
