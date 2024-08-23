package com.ite.cookeat.domain.sskcook.controller;

import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookRes;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookReq;
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
  public ResponseEntity<List<GetSearchSskcookRes>> findSearchSskcookList(
      @RequestParam String keyword,
      @RequestParam Integer page,
      @RequestParam(defaultValue = "latest") String sort) {

    GetSearchSskcookReq modifiedReq = GetSearchSskcookReq.builder()
        .keyword(keyword)
        .page(page)
        .sort(sort)
        .build();

    List<GetSearchSskcookRes> response;

    if ("likes".equals(sort)) {
      response = sskcookService.findSearchLikesSskcook(modifiedReq);
    } else {
      response = sskcookService.findSearchRecentSskcook(modifiedReq);
    }

    return ResponseEntity.ok(response);
  }

}
