package com.ite.cookeat.domain.longcook.controller;

import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import com.ite.cookeat.domain.longcook.service.LongcookService;
import com.ite.cookeat.domain.sskcook.dto.GetLongcookPageRes;
import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/longcook")
public class LongcookController {

  private final LongcookService longcookService;

  @GetMapping("/list/{username}")
  public ResponseEntity<GetLongcookPageRes> findLongcookList(
      @PathVariable("username") String username, @RequestParam(defaultValue = "1") Integer page) {
    return ResponseEntity.ok(longcookService.findLongcookList(username, page));
  }

  @GetMapping("/{longcookId}")
  public ResponseEntity<GetLongcookRes> longcookDetail(@PathVariable Integer longcookId) {
    return ResponseEntity.ok(longcookService.findLongcook(longcookId));
  }

  @GetMapping
  public ResponseEntity<GetLongcookPageRes> findLongcookRecentList(
      @RequestParam(defaultValue = "latest") String sort,
      @RequestParam(defaultValue = "1") Integer page) {
    if ("latest".equals(sort)) {
      return ResponseEntity.ok(longcookService.findRecentLongcookList(page));
    }
    throw new CustomException(ErrorCode.LONGCOOK_NOT_FOUND);
  }
}
