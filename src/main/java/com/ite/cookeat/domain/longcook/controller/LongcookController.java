package com.ite.cookeat.domain.longcook.controller;

import com.ite.cookeat.domain.longcook.dto.GetLongCookPageRes;
import com.ite.cookeat.domain.longcook.service.LongcookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/longcook")
@RequiredArgsConstructor
public class LongcookController {

  private final LongcookService longcookService;

  @GetMapping("/list/{username}")
  public ResponseEntity<GetLongCookPageRes> findLongcookList(
      @PathVariable("username") String username, @RequestParam(defaultValue = "1") Integer page) {
    return ResponseEntity.ok(longcookService.findLongcookList(username, page));
  }
}
