package com.ite.cookeat.domain.longcook.controller;

import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import com.ite.cookeat.domain.longcook.dto.GetTotalLongcookDetailsRes;
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

/**
 * 스윽쿡 (등록 및 조회, 수정, 삭제)
 *
 * @author 박유진
 * @version 1.0
 * @since 2024.08.19
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.19    박유진       최초 생성
 * 2024.08.23    양재혁       멤버 별 스윽쿡 리스트 조회 API
 * 2024.08.24    양재혁       최신 순 스윽쿡 조회 API
 * 2024.08.25    박유진       스윽쿡 삭제 API
 * 2024.08.26    박유진       슥윽쿡 수정 및 등록 API
 * 2024.08.26    박유진       슥윽쿡 검색 및 조회 API
 * 2024.09.02    박유진       비로그인 상태에서 스윽쿡 조회 가능하도록 수정
 * </pre>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/longcook")
public class LongcookController {

  private final LongcookService longcookService;

  @GetMapping("/list/{username}")
  public ResponseEntity<PaginatedRes<GetLongcookRes>> findLongcookList(
      @PathVariable("username") String username, @RequestParam(defaultValue = "1") Integer page) {
    return ResponseEntity.ok(longcookService.findCreatorLongcookList(username, page));
  }

  @GetMapping("/{longcookId}")
  public ResponseEntity<GetTotalLongcookDetailsRes> longcookDetails(
      @PathVariable Integer longcookId) {
    return ResponseEntity.ok(longcookService.findLongcookTotalDetails(longcookId));
  }

  @GetMapping
  public ResponseEntity<PaginatedRes<GetLongcookRes>> findLongcookRecentList(
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
