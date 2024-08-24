package com.ite.cookeat.domain.sskcook.controller;

import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookPageRes;
import com.ite.cookeat.domain.sskcook.service.SskcookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sskcook")
public class SskcookController {

  private final SskcookService sskcookService;

  @GetMapping("/fridge/{username}")
  public ResponseEntity<List<GetFridgeRecipeRes>> recommendFridgeList(
      @PathVariable String username) {
    return ResponseEntity.ok(sskcookService.findMyFridgeRecipe(username));
  }

  @GetMapping
  public ResponseEntity<GetSearchSskcookPageRes> findSearchSskcookList(
      @RequestParam(value = "keyword", required = false) String keyword,
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "latest") String sort,
      @RequestParam(defaultValue = "", required = false) String date) {

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
}
