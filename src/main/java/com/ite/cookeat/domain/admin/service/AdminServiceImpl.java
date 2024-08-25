package com.ite.cookeat.domain.admin.service;

import static com.ite.cookeat.exception.ErrorCode.MEMBER_NOT_FOUND;

import com.ite.cookeat.domain.admin.dto.GetReportSskcookPageRes;
import com.ite.cookeat.domain.admin.dto.GetVerifyRequestPageRes;
import com.ite.cookeat.domain.admin.dto.PostVerifyRequestReq;
import com.ite.cookeat.domain.admin.mapper.AdminMapper;
import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.global.dto.Criteria;
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
  public GetVerifyRequestPageRes findVerifyRequestList(Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .build();

    return GetVerifyRequestPageRes.builder()
        .total(adminMapper.selectVerifyRequestCount())
        .cri(cri)
        .requests(adminMapper.selectVerifyRequestList(cri))
        .build();
  }

  @Override
  @Transactional
  public Integer modifyVerifyMemberStatus(PostVerifyRequestReq req) {
    Integer result = adminMapper.updateVerifyRequestMemberStatus(req);
    if (result <= 0) {
      throw new CustomException(MEMBER_NOT_FOUND);
    }
    return result;
  }

  @Override
  @Transactional(readOnly = true)
  public GetReportSskcookPageRes findReportSskcookList(Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .build();

    return GetReportSskcookPageRes.builder()
        .cri(cri)
        .reports(adminMapper.selectReportSskcookList(cri))
        .total(adminMapper.selectReportSskcookCount())
        .build();
  }
}
