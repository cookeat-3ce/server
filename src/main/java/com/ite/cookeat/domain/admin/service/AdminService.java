package com.ite.cookeat.domain.admin.service;

import com.ite.cookeat.domain.admin.dto.GetReportSskcookPageRes;
import com.ite.cookeat.domain.admin.dto.GetVerifyRequestRes;
import com.ite.cookeat.domain.admin.dto.PostVerifyRequestReq;
import com.ite.cookeat.global.dto.PaginatedRes;

public interface AdminService {

  PaginatedRes<GetVerifyRequestRes> findVerifyRequestList(Integer page);

  Integer modifyVerifyMemberStatusVerified(PostVerifyRequestReq req);

  Integer modifyVerifyMemberStatusUnverified(String username);

  GetReportSskcookPageRes findReportSskcookList(Integer page);

  Integer modifyReportSskcookStatus(Integer sskcookId);
}
