package com.ite.cookeat.domain.sskcook.controller;

import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookRes;
import com.ite.cookeat.domain.sskcook.dto.GetTotalSskcookDetailsRes;
import com.ite.cookeat.domain.sskcook.service.SskcookService;
import com.ite.cookeat.global.dto.PaginatedRes;
import java.io.IOException;
import java.util.List;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sskcook")
public class SskcookController {

  private final SskcookService sskcookService;

  @GetMapping("/{sskcookId}")
  public ResponseEntity<GetTotalSskcookDetailsRes> sskcookDetails(@PathVariable Integer sskcookId,
      @RequestBody String username) throws IOException {
    return ResponseEntity.ok(sskcookService.findSskcookTotalDetails(username, sskcookId));
  }

  @GetMapping("/fridge/{username}")
  public ResponseEntity<List<GetFridgeRecipeRes>> recommendFridgeList(
      @PathVariable String username) {
    return ResponseEntity.ok(sskcookService.findMyFridgeRecipe(username));
  }

  @PostMapping(consumes = {"multipart/form-data"})
  public ResponseEntity<Integer> sskcookAdd(
      @RequestPart("file") MultipartFile file,
      @RequestPart("sskcook") String request) throws IOException {
    return ResponseEntity.ok(sskcookService.addSskcook(request, file));
  }

  @GetMapping
  public ResponseEntity<PaginatedRes<GetSearchSskcookRes>> findSearchSskcookList(
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
  public ResponseEntity<PaginatedRes<GetSearchSskcookRes>> findUserSskcookList(
      @PathVariable String username, @RequestParam(defaultValue = "1") Integer page) {
    return ResponseEntity.ok(sskcookService.findUserSskcookList(username, page));
  }

  @DeleteMapping("/{sskcookId}")
  public ResponseEntity<?> sskcookDelete(@PathVariable Integer sskcookId) {
    sskcookService.modifySskcookDeletedate(sskcookId);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/likes")
  public ResponseEntity<String> sskcookLikesSave(@RequestParam("username") String username,
      @RequestParam("sskcookId") Integer sskcookId) {

    int cnt = sskcookService.findLikes(username, sskcookId);
    if (cnt > 0) {
      sskcookService.removeLikes(username, sskcookId);
      return ResponseEntity.ok("likes deleted");
    }
    sskcookService.addLikes(username, sskcookId);
    return ResponseEntity.ok("likes added");
  }
}
