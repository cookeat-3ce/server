package com.ite.cookeat.domain.longcook.controller;

import com.ite.cookeat.domain.longcook.dto.GetLongcookDetailRes;
import com.ite.cookeat.domain.longcook.service.LongcookService;
import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.exception.ErrorCode;
import com.ite.cookeat.global.dto.PaginatedRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

  @GetMapping("/list/{username}")
  public ResponseEntity<PaginatedRes<GetLongcookDetailRes>> findLongcookList(
      @PathVariable("username") String username, @RequestParam(defaultValue = "1") Integer page) {
    return ResponseEntity.ok(longcookService.findCreatorLongcookList(username, page));
  }

  @GetMapping("/{longcookId}")
  public ResponseEntity<GetLongcookDetailRes> longcookDetail(@PathVariable Integer longcookId) {
    return ResponseEntity.ok(longcookService.findLongcook(longcookId));
  }

  @GetMapping
  public ResponseEntity<PaginatedRes<GetLongcookDetailRes>> findLongcookRecentList(
      @RequestParam(required = false) String keyword,
      @RequestParam(defaultValue = "latest") String sort,
      @RequestParam(defaultValue = "1") Integer page) {
    if ("latest".equals(sort)) {
      return ResponseEntity.ok(longcookService.findRecentLongcookList(keyword, page));
    }
    throw new CustomException(ErrorCode.LONGCOOK_NOT_FOUND);
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

  @PostMapping(consumes = {"multipart/form-data"})
  public ResponseEntity<Integer> longcookAdd(
      @RequestPart("file") MultipartFile file,
      @RequestPart("longcook") String request) {
    return ResponseEntity.ok(longcookService.addLongcook(request, file));
  }
}
