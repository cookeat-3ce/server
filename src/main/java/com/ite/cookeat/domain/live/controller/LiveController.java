package com.ite.cookeat.domain.live.controller;

import com.ite.cookeat.domain.live.dto.GetLiveRes;
import com.ite.cookeat.domain.live.dto.PostLiveReq;
import com.ite.cookeat.domain.live.service.LiveService;
import com.ite.cookeat.global.dto.PaginatedRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/live")
public class LiveController {

  private final LiveService liveService;

  @PostMapping
  public ResponseEntity<Integer> liveAdd(@RequestBody PostLiveReq req) {
    return ResponseEntity.ok(liveService.saveLive(req));
  }

  @GetMapping
  public ResponseEntity<PaginatedRes<GetLiveRes>> liveList(
      @RequestParam(value = "keyword", required = false) String keyword,
      @RequestParam(defaultValue = "1") Integer page) {
    return ResponseEntity.ok(liveService.findLiveList(keyword, page));
  }

  @DeleteMapping("/{liveId}")
  public ResponseEntity<?> liveRemove(@PathVariable Integer liveId) {
    liveService.modifyLiveEnddate(liveId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{sessionId}")
  public ResponseEntity<GetLiveRes> getLive(@PathVariable String sessionId) {
    return ResponseEntity.ok(liveService.findLiveDetail(sessionId));
  }
}