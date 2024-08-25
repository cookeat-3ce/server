package com.ite.cookeat.domain.member_sskcook.controller;

import com.ite.cookeat.domain.member.service.MemberService;
import com.ite.cookeat.domain.member_sskcook.dto.GetMemberSskcookPageRes;
import com.ite.cookeat.domain.member_sskcook.dto.PostMemberSskcookReq;
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
  private final MemberService memberService;

  @PostMapping
  public ResponseEntity<String> MemberSskcookSave(@RequestParam("username") String username,
      @RequestParam("sskcookId") Integer sskcookId) {
    PostMemberSskcookReq modifiedReq = PostMemberSskcookReq.builder()
        .memberId(memberService.findMemberId(username))
        .sskcookId(sskcookId)
        .build();
    int cnt = memberSskcookService.findMemberSskcook(modifiedReq);
    if (cnt > 0) {
      memberSskcookService.removeMemberSskcook(modifiedReq);
      return ResponseEntity.ok("store failed");
    }
    memberSskcookService.addMemberSskcook(modifiedReq);
    return ResponseEntity.ok("store completed");
  }

  @GetMapping("/{username}")
  public ResponseEntity<GetMemberSskcookPageRes> findMemberSskcookList(
      @PathVariable String username, @RequestParam Integer page) {
    return ResponseEntity.ok(memberSskcookService.findMemberSskcookList(username, page));
  }
}
