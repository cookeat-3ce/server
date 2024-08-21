package com.ite.cookeat.domain.member.controller;


import com.ite.cookeat.domain.member.dto.PostLoginReq;
import com.ite.cookeat.domain.member.dto.PostLoginRes;
import com.ite.cookeat.domain.member.dto.PostSignUpReq;
import com.ite.cookeat.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    private final static String AUTHORIZATION_HEADER = "auth";

    private static final String PREFIX = "Bearer ";

    @PostMapping("/sign-up")
    public ResponseEntity<String> addMember(@RequestBody PostSignUpReq postSignUpReq) {
        memberService.addMember(postSignUpReq);
        return ResponseEntity.ok("success");
    }

    @PostMapping("/login")
    public ResponseEntity<PostLoginRes> login(HttpServletResponse response, @RequestBody PostLoginReq postLoginReq) {
        PostLoginRes postLoginRes = memberService.login(postLoginReq);
        response.setHeader(AUTHORIZATION_HEADER, PREFIX + postLoginRes.getAccessToken());
        PostLoginRes modifiedRes = PostLoginRes.builder()
                .nickname(postLoginRes.getNickname())
                .profileImage(postLoginRes.getProfileImage())
                .build();
        return ResponseEntity.ok(modifiedRes);
    }

}
