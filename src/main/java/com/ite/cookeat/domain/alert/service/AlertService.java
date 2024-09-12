package com.ite.cookeat.domain.alert.service;

import com.ite.cookeat.domain.alert.dto.PostEventAlertMemberReq;
import com.ite.cookeat.domain.alert.dto.PostEventAlertReq;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface AlertService {

  Integer addEventAlertMember(PostEventAlertMemberReq req);

  void sendAlert(PostEventAlertReq req);

  SseEmitter createSseEmitter();
}
