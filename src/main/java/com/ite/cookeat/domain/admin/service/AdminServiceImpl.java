package com.ite.cookeat.domain.admin.service;

import static com.ite.cookeat.exception.ErrorCode.EVENT_CONFLICT;
import static com.ite.cookeat.exception.ErrorCode.REPORTED_SSKCOOK_CONFLICT;
import static com.ite.cookeat.exception.ErrorCode.VERIFY_REQUEST_NOT_FOUND;

import com.ite.cookeat.domain.admin.dto.DeleteReportSskcookReq;
import com.ite.cookeat.domain.admin.dto.GetReportSskcookRes;
import com.ite.cookeat.domain.admin.dto.GetVerifyRequestRes;
import com.ite.cookeat.domain.admin.dto.PostEventReq;
import com.ite.cookeat.domain.admin.dto.PostVerifyRequestReq;
import com.ite.cookeat.domain.admin.mapper.AdminMapper;
import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.global.dto.Criteria;
import com.ite.cookeat.global.dto.PaginatedRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
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
@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

  private final AdminMapper adminMapper;

  /**
   * 사용자 인증 요청 목록 조회(페이징)
   *
   * @param page
   * @return 들어온 사용자 인증 요청 목록의 페이징 데이터
   */
  @Override
  @Transactional(readOnly = true)
  public PaginatedRes<GetVerifyRequestRes> findVerifyRequestList(Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .build();

    return PaginatedRes.<GetVerifyRequestRes>builder()
        .total(adminMapper.selectVerifyRequestCount())
        .cri(cri)
        .data(adminMapper.selectVerifyRequestList(cri))
        .build();
  }

  /**
   * 사용자 인증 요청 승인
   *
   * @param req (인증 요청한 사용자 정보)
   * @return 1 이상이면 승인 처리 요청 성공
   */
  @Override
  @Transactional
  public Integer modifyVerifyMemberStatusVerified(PostVerifyRequestReq req) {
    Integer result = adminMapper.updateVerifyRequestMemberStatus(req.getUsername(),
        "VERIFIED");
    if (result <= 0) {
      throw new CustomException(VERIFY_REQUEST_NOT_FOUND);
    }
    return result;
  }

  /**
   * 사용자 인증 요청 반려
   *
   * @param username (인증 요청한 사용자의 username)
   * @return 1 이상이면 승인 처리 요청 성공
   */
  @Override
  @Transactional
  public Integer modifyVerifyMemberStatusUnverified(String username) {
    Integer result = adminMapper.updateVerifyRequestMemberStatus(username,
        "UNVERIFIED");
    if (result <= 0) {
      throw new CustomException(VERIFY_REQUEST_NOT_FOUND);
    }
    return result;
  }

  /**
   * 신고된 슥쿡 목록 조회(페이징)
   *
   * @param page
   * @return 신고된 슥쿡 목록의 페이징 데이터
   */
  @Override
  @Transactional(readOnly = true)
  public PaginatedRes<GetReportSskcookRes> findReportSskcookList(Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .build();

    return PaginatedRes.<GetReportSskcookRes>builder()
        .cri(cri)
        .total(adminMapper.selectReportSskcookCount())
        .data(adminMapper.selectReportSskcookList(cri))
        .build();
  }

  /**
   * 신고된 슥쿡 영상 BLOCKED 처리
   *
   * @param sskcookId
   * @return 1 이상이면 BLOCKED 요청 처리 성공
   */
  @Override
  @Transactional
  public Integer modifyReportSskcookStatus(Integer sskcookId) {
    DeleteReportSskcookReq req = DeleteReportSskcookReq.builder()
        .sskcookId(sskcookId)  // IN 파라미터 설정
        .result(null)          // OUT 파라미터 초기화
        .build();

    adminMapper.updateReportSskcookStatus(req);

    Integer result = req.getResult();
    if (result == null || result <= 0) {
      throw new CustomException(REPORTED_SSKCOOK_CONFLICT);
    }
    return result;
  }

  /**
   * 이벤트 등록
   *
   * @param req (등록할 이벤트 정보)
   * @return 1 이상이면 이벤트 등록 요청 성공
   */
  @Override
  @Transactional
  public Integer addEvent(PostEventReq req) {
    Integer result = adminMapper.insertEvent(req);
    if (result <= 0) {
      throw new CustomException(EVENT_CONFLICT);
    }
    return result;
  }
}
