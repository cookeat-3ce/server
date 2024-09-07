package com.ite.cookeat.domain.admin.service;

import static com.ite.cookeat.exception.ErrorCode.EVENT_CONFLICT;
import static com.ite.cookeat.exception.ErrorCode.REPORTED_SSKCOOK_CONFLICT;
import static com.ite.cookeat.exception.ErrorCode.VERIFY_REQUEST_NOT_FOUND;

import com.ite.cookeat.domain.admin.dto.PostEventReq;
import com.ite.cookeat.domain.admin.dto.DeleteReportSskcookReq;
import com.ite.cookeat.domain.admin.dto.GetReportSskcookRes;
import com.ite.cookeat.domain.admin.dto.GetVerifyRequestRes;
import com.ite.cookeat.domain.admin.dto.PostVerifyRequestReq;
import com.ite.cookeat.domain.admin.mapper.AdminMapper;
import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.global.dto.Criteria;
import com.ite.cookeat.global.dto.PaginatedRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

  private final AdminMapper adminMapper;

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

  @Override
  @Transactional
  public Integer modifyReportSskcookStatus(Integer sskcookId) {
    // DTO 객체 생성
    DeleteReportSskcookReq req = DeleteReportSskcookReq.builder()
        .sskcookId(sskcookId)  // IN 파라미터 설정
        .result(null)          // OUT 파라미터 초기화
        .build();

    // 매퍼 메서드 호출
    adminMapper.updateReportSskcookStatus(req);

    // OUT 파라미터 값 확인
    Integer result = req.getResult();
    if (result == null || result <= 0) {
      throw new CustomException(REPORTED_SSKCOOK_CONFLICT);
    }
    return result;
  }

  @Override
  public Integer addEvent(PostEventReq req) {
    Integer result = adminMapper.insertEvent(req);
    if (result <= 0) {
      throw new CustomException(EVENT_CONFLICT);
    }
    return result;
  }
}
