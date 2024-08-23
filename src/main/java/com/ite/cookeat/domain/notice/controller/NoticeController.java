package com.ite.cookeat.domain.notice.controller;

import com.ite.cookeat.domain.notice.dto.PostNoticeReq;
import com.ite.cookeat.domain.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notice")
public class NoticeController {

  private final NoticeService noticeService;

  @PostMapping
  public ResponseEntity<Integer> noticeAdd(@RequestBody PostNoticeReq req) {
    return ResponseEntity.ok(noticeService.addNotice(req));
  }
}
