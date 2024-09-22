package com.ite.cookeat.domain.notice.controller;

import com.ite.cookeat.domain.notice.dto.PostNoticeReq;
import com.ite.cookeat.domain.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 사용자 공지 (공지 등록, 삭제)
 *
 * @author 김지수
 * @version 1.0
 * @since 2024.08.23
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.23    김지수       최초 생성, 공지 등록, 삭제
 * </pre>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notice")
public class NoticeController {

  private final NoticeService noticeService;

  @PostMapping
  public ResponseEntity<Integer> noticeAdd(@RequestBody PostNoticeReq req) {
    return ResponseEntity.ok(noticeService.addNotice(req));
  }

  @DeleteMapping("/{noticeId}")
  public ResponseEntity<?> noticeDelete(@PathVariable Integer noticeId) {
    noticeService.modifyNoticeDeletedate(noticeId);
    return ResponseEntity.noContent().build();
  }
}
