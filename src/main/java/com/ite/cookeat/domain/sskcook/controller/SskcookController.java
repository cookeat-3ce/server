package com.ite.cookeat.domain.sskcook.controller;

import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import com.ite.cookeat.domain.sskcook.service.SskcookService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
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