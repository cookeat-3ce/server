package com.ite.cookeat.domain.member.controller;

import com.ite.cookeat.domain.member.dto.GetUserDetailsRes;
import com.ite.cookeat.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/{username}")
  public ResponseEntity<GetUserDetailsRes> findUserDetails(@PathVariable String username) {

    GetUserDetailsRes getUserDetailsRes = memberService.findUserDetailsByUsername(username);

    return ResponseEntity.ok(getUserDetailsRes);
  }
}
