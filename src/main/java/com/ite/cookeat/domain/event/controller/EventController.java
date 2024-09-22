package com.ite.cookeat.domain.event.controller;

import com.ite.cookeat.domain.event.dto.GetEventDetailRes;
import com.ite.cookeat.domain.event.dto.GetEventRes;
import com.ite.cookeat.domain.event.service.EventService;
import com.ite.cookeat.global.dto.PaginatedRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 이벤트 (이벤트 목록 및 상세 조회)
 *
 * @author 김지수
 * @version 1.0
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.25    김지수       최초 생성, 이벤트 목록 및 상세 조회 API
 * 2024.09.21    김지수       이벤트 목록 조회 API에 filtering 조건 추가
 * </pre>
 * @since 2024.08.25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/event")
public class EventController {

  private final EventService eventService;

  @GetMapping("/{eventId}")
  public ResponseEntity<GetEventDetailRes> eventDetail(@PathVariable Integer eventId) {
    return ResponseEntity.ok(eventService.findEventDetail(eventId));
  }

  @GetMapping
  public ResponseEntity<PaginatedRes<GetEventRes>> eventList(
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(required = false) String filtering) {
    return ResponseEntity.ok(eventService.findEventList(page, filtering));
  }
}
