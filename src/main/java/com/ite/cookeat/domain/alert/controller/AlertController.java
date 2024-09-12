package com.ite.cookeat.domain.alert.controller;

import com.ite.cookeat.domain.alert.dto.PostEventAlertMemberReq;
import com.ite.cookeat.domain.alert.dto.PostEventAlertReq;
import com.ite.cookeat.domain.alert.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/alert")
public class AlertController {

  private final AlertService alertService;

  @PostMapping("/event")
  public ResponseEntity<Integer> eventAlertMemberAdd(@RequestBody PostEventAlertMemberReq req) {
    return ResponseEntity.ok(alertService.addEventAlertMember(req));
  }

  @GetMapping(path = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public ResponseEntity<SseEmitter> streamAlerts() {
    return ResponseEntity.ok(alertService.createSseEmitter());
  }

  @PostMapping("/send")
  public ResponseEntity<Void> sendAlert(@RequestBody PostEventAlertReq req) {
    alertService.sendAlert(req);
    return ResponseEntity.ok().build();
  }
}
