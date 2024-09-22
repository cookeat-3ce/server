package com.ite.cookeat.domain.admin.service;

import com.ite.cookeat.domain.admin.dto.GetReportSskcookRes;
import com.ite.cookeat.domain.admin.dto.GetVerifyRequestRes;
import com.ite.cookeat.domain.admin.dto.PostEventReq;
import com.ite.cookeat.domain.admin.dto.PostVerifyRequestReq;
import com.ite.cookeat.global.dto.PaginatedRes;

/**
 * 이벤트 등록, 사용자 인증 등을 처리하는 Service
 *
 * @author 김지수
 * @version 1.0
 * @since 2024.08.25
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.25    김지수       최초 생성, 사용자 인증
 * 2024.08.26    김지수       이벤트 등록, 신고된 영상 목록 조회 및 BLOCKED 처리, 사용자 인증 요청 상태 변경(승인, 반려)
 * </pre>
 */
public interface AdminService {

  // 사용자 인증 요청 페이징 조회
  PaginatedRes<GetVerifyRequestRes> findVerifyRequestList(Integer page);

  // 이벤트 등록
  Integer addEvent(PostEventReq req);

  // 사용자 인증 요청 승인
  Integer modifyVerifyMemberStatusVerified(PostVerifyRequestReq req);

  // 사용자 인증 요청 반려
  Integer modifyVerifyMemberStatusUnverified(String username);

  // 신고된 슥쿡의 상태 변경
  Integer modifyReportSskcookStatus(Integer sskcookId);

  // 신고된 슥쿡 목록 조회
  PaginatedRes<GetReportSskcookRes> findReportSskcookList(Integer page);
}
