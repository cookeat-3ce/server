package com.ite.cookeat.domain.admin.service;

import com.ite.cookeat.domain.admin.dto.GetVerifyRequestPageRes;

public interface AdminService {

  GetVerifyRequestPageRes findVerifyRequestList(Integer page);
}
