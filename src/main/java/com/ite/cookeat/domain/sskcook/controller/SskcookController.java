package com.ite.cookeat.domain.sskcook.controller;

import com.ite.cookeat.domain.admin.dto.GetTopSskcookRes;
import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookRes;
import com.ite.cookeat.domain.sskcook.dto.GetTotalSskcookDetailsRes;
import com.ite.cookeat.domain.sskcook.dto.PostLikesReq;
import com.ite.cookeat.domain.sskcook.service.SskcookService;
import com.ite.cookeat.global.dto.PaginatedRes;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 슥쿡 등록 및 조회, 상세 기능(좋아요, 저장, 신고) API
 *
 * @author 박유진
 * @version 1.0
 * @since 2024.08.19
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.19    박유진       최초 생성
 * 2024.08.23    박유진       냉장고 속 슥쿡 추천 API
 * 2024.08.23    양재혁       슥쿡 검색 API
 * 2024.08.24    박유진       슥쿡 삭제 및 등록 API
 * 2024.08.25    양재혁       좋아요 API
 * 2024.08.27    박유진       슥쿡 수정 API
 * 2024.08.31    양재혁       신고 API
 * </pre>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sskcook")
public class SskcookController {

  private final SskcookService sskcookService;

  @GetMapping("/{sskcookId}")
  public ResponseEntity<GetTotalSskcookDetailsRes> sskcookDetails(@PathVariable Integer sskcookId) {
    return ResponseEntity.ok(sskcookService.findSskcookTotalDetails(sskcookId));
  }

  @GetMapping("/fridge")
  public ResponseEntity<List<GetFridgeRecipeRes>> recommendFridgeList() {
    return ResponseEntity.ok(sskcookService.findMyFridgeRecipe());
  }

  @PostMapping(consumes = {"multipart/form-data"})
  public ResponseEntity<Integer> sskcookAdd(
      @RequestPart("file") MultipartFile file,
      @RequestPart("sskcook") String request) {
    return ResponseEntity.ok(sskcookService.addSskcook(request, file));
  }

  @GetMapping
  public ResponseEntity<PaginatedRes<GetSearchSskcookRes>> sskcookSearchList(
      @RequestParam(value = "keyword", required = false) String keyword,
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "latest") String sort,
      @RequestParam(defaultValue = "", required = false) String tag,
      @RequestParam(defaultValue = "", required = false) String date) {

    if (!tag.isEmpty()) {
      return ResponseEntity.ok(sskcookService.findTagSskcookList(tag, page));
    }

    if (!date.isEmpty()) {
      return ResponseEntity.ok(sskcookService.findMonthlySskcookList(date, page));
    }

    // 최신순 10개
    if (keyword == null) {
      return ResponseEntity.ok(sskcookService.findRecentSskcookList(page));
    }

    if ("latest".equals(sort)) {
      return ResponseEntity.ok(sskcookService.findSearchRecentSskcookList(keyword, page));
    }

    return ResponseEntity.ok(sskcookService.findSearchLikesSskcookList(keyword, page));
  }

  @GetMapping("/list/{username}")
  public ResponseEntity<PaginatedRes<GetSearchSskcookRes>> sskcookMemberList(
      @PathVariable String username, @RequestParam(defaultValue = "1") Integer page) {
    return ResponseEntity.ok(sskcookService.findUserSskcookList(username, page));
  }

  @DeleteMapping("/{sskcookId}")
  public ResponseEntity<?> sskcookDelete(@PathVariable Integer sskcookId) {
    sskcookService.modifySskcookDeletedate(sskcookId);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/likes")
  public ResponseEntity<String> sskcookLikesSave(@RequestBody PostLikesReq req) {
    int cnt = sskcookService.findLikes(req);
    if (cnt > 0) {
      sskcookService.removeLikes(req);
      return ResponseEntity.ok("likes deleted");
    }
    sskcookService.addLikes(req);
    return ResponseEntity.ok("likes added");
  }

  @PostMapping("/report")
  public ResponseEntity<String> sskcookReportSave(@RequestBody PostLikesReq postLikesReq) {
    int cnt = sskcookService.findReport(postLikesReq);
    System.out.println(cnt);
    if (cnt > 0) {
      sskcookService.removeReport(postLikesReq);
      return ResponseEntity.ok("report deleted");
    }
    sskcookService.addReport(postLikesReq);
    return ResponseEntity.ok("report added");
  }

  @PutMapping(consumes = {"multipart/form-data"})
  public ResponseEntity<?> sskcookModify(
      @RequestPart("file") MultipartFile file,
      @RequestPart("sskcook") String request) {
    return ResponseEntity.ok(sskcookService.modifySskcook(request, file));
  }

  @GetMapping("/top")
  public ResponseEntity<List<GetTopSskcookRes>> topSskcooksList(@RequestParam String date) {
    return ResponseEntity.ok(sskcookService.findTopSskcookList(date));
  }
}
