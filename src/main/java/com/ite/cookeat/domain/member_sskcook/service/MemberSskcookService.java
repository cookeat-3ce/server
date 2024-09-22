package com.ite.cookeat.domain.member_sskcook.service;

import com.ite.cookeat.domain.member_sskcook.dto.GetMemberSskcookRes;
import com.ite.cookeat.domain.member_sskcook.dto.PostMemberSskcookReq;
import com.ite.cookeat.global.dto.PaginatedRes;

/**
 * 보관함과 관련된 요청을 처리하는 Service
 *
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
public interface MemberSskcookService {

  // 보관하기
  void addMemberSskcook(PostMemberSskcookReq req);

  // 보관 삭제
  void removeMemberSskcook(PostMemberSskcookReq req);

  // 보관함 내 여부 확인
  Integer findMemberSskcook(PostMemberSskcookReq req);

  // 보관함 조회
  PaginatedRes<GetMemberSskcookRes> findMemberSskcookList(Integer page);
}
