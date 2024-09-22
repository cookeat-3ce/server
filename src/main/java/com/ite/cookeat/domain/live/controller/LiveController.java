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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 실시간 요리 클래스 (진행 중인 라이브 목록 조회, 등록, 라이브 종료, 라이브 입장)
 *
 * @author 김지수
 * @version 1.0
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.23    김지수       최초 생성, 라이브 등록
 * 2024.08.25    박유진       라이브 목록 조회
 * 2024.09.04    김지수       라이브 입장, 종료
 * </pre>
 * @since 2024.08.23
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/live")
public class LiveController {

  private final LiveService liveService;

  @PostMapping(consumes = {"multipart/form-data"})
  public ResponseEntity<Integer> liveAdd(
      @RequestPart("live") String req,
      @RequestPart("file") MultipartFile file) {
    return ResponseEntity.ok(liveService.saveLive(req, file));
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