package com.ite.cookeat.domain.event.controller;

import com.ite.cookeat.domain.event.dto.GetEventDetailRes;
import com.ite.cookeat.domain.event.dto.GetEventPageRes;
import com.ite.cookeat.domain.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/event")
public class EventController {

  private final EventService eventService;

  @GetMapping("/{eventId}")
  public ResponseEntity<GetEventDetailRes> eventList(@PathVariable Integer eventId) {
    return ResponseEntity.ok(eventService.findEventDetail(eventId));
  }

  @GetMapping
  public ResponseEntity<GetEventPageRes> eventDetail(
      @RequestParam(defaultValue = "1") Integer page) {
    return ResponseEntity.ok(eventService.findEventList(page));
  }
}

