package com.ite.cookeat.domain.admin.controller;

import com.ite.cookeat.domain.admin.dto.GetVerifyRequestPageRes;
import com.ite.cookeat.domain.admin.dto.PostVerifyRequestReq;
import com.ite.cookeat.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

  private final AdminService adminService;

  @GetMapping("/member/verify")
  public ResponseEntity<GetVerifyRequestPageRes> verifyRequestList(@RequestParam Integer page) {
    return ResponseEntity.ok(adminService.findVerifyRequestList(page));
  }

  @PostMapping("/member/verify")
  public ResponseEntity<Integer> verifyRequestAdd(@RequestBody PostVerifyRequestReq req) {
    return ResponseEntity.ok(adminService.modifyVerifyMemberStatusVerified(req));
  }

  @DeleteMapping("/member/verify/{username}")
  public ResponseEntity<?> verifyRequestDelete(@PathVariable String username) {
    return ResponseEntity.ok(adminService.modifyVerifyMemberStatusUnverified(username));
  }
}
