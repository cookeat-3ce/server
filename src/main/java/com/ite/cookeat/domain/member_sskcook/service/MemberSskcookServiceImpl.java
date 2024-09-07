package com.ite.cookeat.domain.member_sskcook.service;

import com.ite.cookeat.domain.member.service.MemberService;
import com.ite.cookeat.domain.member_sskcook.dto.GetMemberSskcookRes;
import com.ite.cookeat.domain.member_sskcook.dto.PostMemberSskcookReq;
import com.ite.cookeat.domain.member_sskcook.mapper.MemberSskcookMapper;
import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.exception.ErrorCode;
import com.ite.cookeat.global.dto.Criteria;
import com.ite.cookeat.global.dto.PaginatedRes;
import com.ite.cookeat.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberSskcookServiceImpl implements MemberSskcookService {

  private final MemberSskcookMapper memberSskcookMapper;
  private final MemberService memberService;

  @Override
  @Transactional
  public void addMemberSskcook(PostMemberSskcookReq req) {
    req.setMemberId(memberService.findMemberId(SecurityUtils.getCurrentUsername()));

    Integer result = memberSskcookMapper.insertMemberSskcook(req);
    if (result == 0) {
      throw new CustomException(ErrorCode.MEMBER_SSKCOOK_INSERT_FAIL);
    }
  }

  @Override
  @Transactional
  public void removeMemberSskcook(PostMemberSskcookReq req) {
    req.setMemberId(memberService.findMemberId(SecurityUtils.getCurrentUsername()));
    Integer result = memberSskcookMapper.deleteMemberSskcook(req);
    if (result == 0) {
      throw new CustomException(ErrorCode.MEMBER_SSKCOOK_DELETE_FAIL);
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Integer findMemberSskcook(PostMemberSskcookReq req) {
    req.setMemberId(memberService.findMemberId(SecurityUtils.getCurrentUsername()));
    return memberSskcookMapper.selectMemberSskcookCount(req);
  }

  @Override
  @Transactional(readOnly = true)
  public PaginatedRes<GetMemberSskcookRes> findMemberSskcookList(Integer page) {
    String username = SecurityUtils.getCurrentUsername();
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .build();

    return PaginatedRes.<GetMemberSskcookRes>builder()
        .cri(cri)
        .total(memberSskcookMapper.selectMemberSskcookListCount(username))
        .data(memberSskcookMapper.selectMemberSskcookList(cri, username))
        .build();
  }
}
