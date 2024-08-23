package com.ite.cookeat.domain.longcook.controller;

import com.ite.cookeat.domain.longcook.dto.GetLongcookReq;
import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import com.ite.cookeat.domain.longcook.service.LongcookService;
import java.util.List;
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

  @GetMapping("/{username}")
  public ResponseEntity<List<GetLongcookRes>> findLongcookList(
      @PathVariable("username") String username, @RequestParam(defaultValue = "1") Integer page) {
    GetLongcookReq modifiedReq = GetLongcookReq.builder().username(username).page(page).build();
    return ResponseEntity.ok(longcookService.findLongcook(modifiedReq));
  }
}
