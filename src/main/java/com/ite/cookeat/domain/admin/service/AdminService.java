package com.ite.cookeat.domain.admin.service;

import com.ite.cookeat.domain.admin.dto.PostEventReq;
import com.ite.cookeat.domain.admin.dto.GetReportSskcookRes;
import com.ite.cookeat.domain.admin.dto.GetTopSskcookRes;
import com.ite.cookeat.domain.admin.dto.GetVerifyRequestRes;
import com.ite.cookeat.domain.admin.dto.PostVerifyRequestReq;
import com.ite.cookeat.global.dto.PaginatedRes;
import java.util.List;

public interface AdminService {

  PaginatedRes<GetVerifyRequestRes> findVerifyRequestList(Integer page);

  Integer addEvent(PostEventReq req);

  Integer modifyVerifyMemberStatusVerified(PostVerifyRequestReq req);

  Integer modifyVerifyMemberStatusUnverified(String username);

  Integer modifyReportSskcookStatus(Integer sskcookId);

  PaginatedRes<GetReportSskcookRes> findReportSskcookList(Integer page);

  List<GetTopSskcookRes> findTopSskcookList(String yearMonth);
}
