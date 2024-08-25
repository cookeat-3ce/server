package com.ite.cookeat.domain.admin.service;

import com.ite.cookeat.domain.admin.dto.GetReportSskcookPageRes;
import com.ite.cookeat.domain.admin.dto.GetVerifyRequestPageRes;
import com.ite.cookeat.domain.admin.dto.PostVerifyRequestReq;

public interface AdminService {

  GetVerifyRequestPageRes findVerifyRequestList(Integer page);

  Integer modifyVerifyMemberStatusVerified(PostVerifyRequestReq req);

  Integer modifyVerifyMemberStatusUnverified(String username);
  
  GetReportSskcookPageRes findReportSskcookList(Integer page);

  Integer modifyReportSskcookStatus(Integer sskcookId);
}
