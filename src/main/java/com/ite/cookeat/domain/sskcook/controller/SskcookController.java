package com.ite.cookeat.domain.sskcook.controller;

import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookReq;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookRes;
import com.ite.cookeat.domain.sskcook.service.SskcookService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

  @PostMapping(consumes = {"multipart/form-data"})
  public ResponseEntity<Integer> sskcookAdd(
      @RequestPart("file") MultipartFile file,
      @RequestPart("sskcook") String request) throws IOException {
    return ResponseEntity.ok(sskcookService.addSskcook(request, file));
  }
}

  @GetMapping
  public ResponseEntity<List<GetSearchSskcookRes>> findSearchSskcookList(
      @RequestParam(value = "keyword", required = false) String keyword,
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "latest") String sort,
      @RequestParam(defaultValue = "", required = false) String date) {

    GetSearchSskcookReq modifiedReq = GetSearchSskcookReq.builder()
        .keyword(keyword)
        .page(page)
        .date(date)
        .sort(sort)
        .build();

    if (!date.isEmpty()) {
      return ResponseEntity.ok(sskcookService.findMonthlySskcook(modifiedReq));
    }

    // 최신순 10개
    if (keyword == null) {
      return ResponseEntity.ok(sskcookService.findRecentSskcook(modifiedReq));
    }

    if ("latest".equals(sort)) {
      return ResponseEntity.ok(sskcookService.findSearchRecentSskcook(modifiedReq));
    }

    return ResponseEntity.ok(sskcookService.findSearchLikesSskcook(modifiedReq));
  }

  @DeleteMapping("/{sskcookId}")
  public ResponseEntity<?> sskcookDelete(@PathVariable Integer sskcookId) {
    sskcookService.modifySskcookDeletedate(sskcookId);
    return ResponseEntity.noContent().build();
  }
}
