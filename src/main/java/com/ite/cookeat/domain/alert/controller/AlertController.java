package com.ite.cookeat.domain.alert.controller;

import com.ite.cookeat.domain.alert.dto.PostEventAlertMemberReq;
import com.ite.cookeat.domain.alert.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/alert")
public class AlertController {

  private final AlertService alertService;

  @PostMapping("/event")
  public ResponseEntity<Integer> eventAlertMemberAdd(@RequestBody PostEventAlertMemberReq req) {
    return ResponseEntity.ok(alertService.addEventAlertMember(req));
  }
}
