package com.ite.cookeat.domain.admin.service;

import static com.ite.cookeat.exception.ErrorCode.MEMBER_NOT_FOUND;

import com.ite.cookeat.domain.admin.dto.GetVerifyRequestRes;
import com.ite.cookeat.domain.admin.dto.PostVerifyRequestReq;
import com.ite.cookeat.domain.admin.mapper.AdminMapper;
import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.global.dto.Criteria;
import com.ite.cookeat.global.dto.PaginatedRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

  private final AdminMapper adminMapper;

  @Override
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
  public Integer modifyVerifyMemberStatus(PostVerifyRequestReq req) {
    Integer result = adminMapper.updateVerifyRequestMemberStatus(req);
    if (result <= 0) {
      throw new CustomException(MEMBER_NOT_FOUND);
    }
    return result;
  }
}
