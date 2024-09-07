package com.ite.cookeat.domain.alert.service;

import com.ite.cookeat.domain.alert.dto.PostEventAlertMemberReq;

public interface AlertService {

  Integer addEventAlertMember(PostEventAlertMemberReq req);
}
