package com.ite.cookeat.domain.member_sskcook.controller;

import com.ite.cookeat.domain.member_sskcook.dto.GetMemberSskcookPageRes;
import com.ite.cookeat.domain.member_sskcook.service.MemberSskcookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member/sskcook")
@RequiredArgsConstructor
public class MemberSskcookController {

  private final MemberSskcookService memberSskcookService;

  @PostMapping
  public ResponseEntity<String> memberSskcookSave(@RequestParam("username") String username,
      @RequestParam("sskcookId") Integer sskcookId) {
    int cnt = memberSskcookService.findMemberSskcook(username, sskcookId);
    if (cnt > 0) {
      memberSskcookService.removeMemberSskcook(username, sskcookId);
      return ResponseEntity.ok("store failed");
    }
    memberSskcookService.addMemberSskcook(username, sskcookId);
    return ResponseEntity.ok("store completed");
  }

  @GetMapping("/{username}")
  public ResponseEntity<GetMemberSskcookPageRes> memberSskcookList(
      @PathVariable String username, @RequestParam Integer page) {
    return ResponseEntity.ok(memberSskcookService.findMemberSskcookList(username, page));
  }
}
