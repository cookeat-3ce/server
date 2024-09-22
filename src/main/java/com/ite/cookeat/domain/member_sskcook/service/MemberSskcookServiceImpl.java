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

/**
 * @author 양재혁
 * @version 1.0
 * @since 2024.08.25
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.25    양재혁       최초 생성
 * </pre>
 */
@Service
@RequiredArgsConstructor
public class MemberSskcookServiceImpl implements MemberSskcookService {

  private final MemberSskcookMapper memberSskcookMapper;
  private final MemberService memberService;

  /**
   * 보관하기
   *
   * @param req
   */
  @Override
  @Transactional
  public void addMemberSskcook(PostMemberSskcookReq req) {
    req.setMemberId(memberService.findMemberId(SecurityUtils.getCurrentUsername()));

    Integer result = memberSskcookMapper.insertMemberSskcook(req);
    if (result == 0) {
      throw new CustomException(ErrorCode.MEMBER_SSKCOOK_INSERT_FAIL);
    }
  }

  /**
   * 보관하기 취소
   *
   * @param req
   */
  @Override
  @Transactional
  public void removeMemberSskcook(PostMemberSskcookReq req) {
    req.setMemberId(memberService.findMemberId(SecurityUtils.getCurrentUsername()));
    Integer result = memberSskcookMapper.deleteMemberSskcook(req);
    if (result == 0) {
      throw new CustomException(ErrorCode.MEMBER_SSKCOOK_DELETE_FAIL);
    }
  }

  /**
   * 보관함 내 여부 확인
   *
   * @param req
   * @return 보관함 내 슥쿡 개수
   */
  @Override
  @Transactional(readOnly = true)
  public Integer findMemberSskcook(PostMemberSskcookReq req) {
    req.setMemberId(memberService.findMemberId(SecurityUtils.getCurrentUsername()));
    return memberSskcookMapper.selectMemberSskcookCount(req);
  }

  /**
   * 보관함 조회
   *
   * @param page
   * @return 보관함 목록 페이징 데이터
   */
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
