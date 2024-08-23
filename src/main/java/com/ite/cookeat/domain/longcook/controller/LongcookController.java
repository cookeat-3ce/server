package com.ite.cookeat.domain.longcook.controller;

import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import com.ite.cookeat.domain.longcook.service.LongcookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/longcook")
public class LongcookController {

  private final LongcookService longcookService;

  @GetMapping("/{longcookId}")
  public ResponseEntity<GetLongcookRes> longcookDetail(@PathVariable Integer longcookId) {
    return ResponseEntity.ok(longcookService.findLongcookService(longcookId));
  }
}
