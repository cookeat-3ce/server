package com.ite.cookeat.domain.member_sskcook.controller;

import com.ite.cookeat.domain.member_sskcook.dto.GetMemberSskcookRes;
import com.ite.cookeat.domain.member_sskcook.dto.PostMemberSskcookReq;
import com.ite.cookeat.domain.member_sskcook.service.MemberSskcookService;
import com.ite.cookeat.global.dto.PaginatedRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member/sskcook")
@RequiredArgsConstructor
public class MemberSskcookController {

  private final MemberSskcookService memberSskcookService;

  @PostMapping("/{sskcookId}")
  public ResponseEntity<String> memberSskcookAdd(
      @RequestBody PostMemberSskcookReq req) {
    Integer cnt = memberSskcookService.findMemberSskcook(req);
    if (cnt > 0) {
      memberSskcookService.removeMemberSskcook(req);
      return ResponseEntity.ok("store deleted");
    }
    memberSskcookService.addMemberSskcook(req);
    return ResponseEntity.ok("store added");
  }

  @GetMapping
  public ResponseEntity<PaginatedRes<GetMemberSskcookRes>> memberSskcookList(
      @RequestParam Integer page) {
    return ResponseEntity.ok(memberSskcookService.findMemberSskcookList(page));
  }
}
