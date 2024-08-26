package com.ite.cookeat.domain.longcook.controller;

import com.ite.cookeat.domain.longcook.dto.GetLongcookReq;
import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import com.ite.cookeat.domain.longcook.service.LongcookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/longcook")
public class LongcookController {

  private final LongcookService longcookService;

  @GetMapping("/{longcookId}")
  public ResponseEntity<GetLongcookRes> longcookDetail(@PathVariable Integer longcookId) {
    return ResponseEntity.ok(longcookService.findLongcook(longcookId));
  }

  @GetMapping("/list/{username}")
  public ResponseEntity<List<GetLongcookRes>> longcookList(
      @PathVariable("username") String username, @RequestParam(defaultValue = "1") Integer page) {
    GetLongcookReq modifiedReq = GetLongcookReq.builder().username(username).page(page).build();
    return ResponseEntity.ok(longcookService.findLongcookList(modifiedReq));
  }

  @DeleteMapping("/{longcookId}")
  public ResponseEntity<?> longcookDelete(@PathVariable Integer longcookId) {
    longcookService.modifyLongcookDeletedate(longcookId);
    return ResponseEntity.noContent().build();
  }

  @PutMapping(consumes = {"multipart/form-data"})
  public ResponseEntity<?> longcookModify(
      @RequestPart("file") MultipartFile file,
      @RequestPart("longcook") String request) {
    return ResponseEntity.ok(longcookService.modifyLongcook(request, file));
  }
}
