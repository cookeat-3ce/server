package com.ite.cookeat.domain.admin.service;

import com.ite.cookeat.domain.admin.dto.GetVerifyRequestRes;
import com.ite.cookeat.domain.admin.dto.PostVerifyRequestReq;
import com.ite.cookeat.global.dto.PaginatedRes;

public interface AdminService {

  PaginatedRes<GetVerifyRequestRes> findVerifyRequestList(Integer page);

  Integer modifyVerifyMemberStatus(PostVerifyRequestReq req);
}
