package com.ite.cookeat.domain.member.controller;

import com.ite.cookeat.domain.member.dto.GetMemberNoticeRes;
import com.ite.cookeat.domain.member.dto.GetSubscriptionMemberDetailsRes;
import com.ite.cookeat.domain.member.dto.GetUserDetailsRes;
import com.ite.cookeat.domain.member.dto.PostLoginReq;
import com.ite.cookeat.domain.member.dto.PostLoginRes;
import com.ite.cookeat.domain.member.dto.PostMemberOneLinerReq;
import com.ite.cookeat.domain.member.dto.PostSignUpReq;
import com.ite.cookeat.domain.member.dto.PostSubscriptionReq;
import com.ite.cookeat.domain.member.service.MemberService;
import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.exception.ErrorCode;
import com.ite.cookeat.global.dto.PaginatedRes;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 멤버 (회원가입, 로그인, 사용자 정보 조회 등)
 *
 * @author 양재혁
 * @version 1.0
 * @since 2024.08.21
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.19    박유진       최초 생성
 * 2024.08.21    양재혁       회원 가입
 * 2024.08.21    양재혁       로그인
 * 2024.08.22    양재혁       멤버 정보 조회
 * 2024.08.23    양재혁       멤버 검색
 * 2024.08.23    김지수       사용자 공지 조회
 * 2024.08.24    김지수       한줄 소개 수정
 * 2024.08.24    박유진       크리에이터 인증 요청
 * 2024.08.25    양재혁       구독 멤버 조회
 * 2024.08.25    박유진       회원 탈퇴
 * 2024.08.26    김지수       사용자 인증 상태 조회
 * 2024.08.27    김지수       사용자 구독
 * </pre>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

  private final static String AUTHORIZATION_HEADER = "auth";
  private static final String PREFIX = "Bearer ";
  private final MemberService memberService;

  @PatchMapping(value = "/{username}/verify", produces = "application/json; charset=UTF-8")
  public ResponseEntity<Integer> verifyStatusModify(@PathVariable String username) {
    return ResponseEntity.ok(memberService.modifyVerifyStatus(username, "REQUEST_VERIFY"));
  }

  @PostMapping("/one-liner")
  public ResponseEntity<Integer> memberOneLinerModify(@RequestBody PostMemberOneLinerReq req) {
    return ResponseEntity.ok(memberService.modifyMemberOneLiner(req));
  }

  @PostMapping("/sign-up")
  public ResponseEntity<String> memberAdd(@RequestBody PostSignUpReq postSignUpReq) {
    memberService.addMember(postSignUpReq);
    return ResponseEntity.ok("success");
  }

  @PostMapping("/login")
  public ResponseEntity<PostLoginRes> login(HttpServletResponse response,
      @RequestBody PostLoginReq postLoginReq) {
    try {
      PostLoginRes postLoginRes = memberService.login(postLoginReq);
      response.setHeader(AUTHORIZATION_HEADER, PREFIX + postLoginRes.getAccessToken());
      PostLoginRes modifiedRes = PostLoginRes.builder()
          .username(postLoginRes.getUsername())
          .nickname(postLoginRes.getNickname())
          .profileImage(postLoginRes.getProfileImage())
          .build();
      return ResponseEntity.ok(modifiedRes);
    } catch (Exception e) {
      throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
    }
  }

  @PatchMapping("/{username}/withdraw")
  public ResponseEntity<Boolean> memberDelete(@PathVariable String username) {
    memberService.modifyMemberDeletedate(username);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{username}")
  public ResponseEntity<GetUserDetailsRes> findUserDetails(@PathVariable String username) {
    return ResponseEntity.ok(memberService.findUserDetailsByUsername(username));
  }

  @GetMapping
  public ResponseEntity<PaginatedRes<GetUserDetailsRes>> findSearchMemberList(
      @RequestParam(value = "keyword", required = false) String keyword,
      @RequestParam(defaultValue = "1") Integer page) {

    return ResponseEntity.ok(memberService.findSearchMember(keyword, page));
  }

  @GetMapping("/{username}/notice")
  public ResponseEntity<PaginatedRes<GetMemberNoticeRes>> memberNoticeList(
      @PathVariable String username,
      @RequestParam Integer page) {
    return ResponseEntity.ok(memberService.findMemberNotices(username, page));
  }

  @GetMapping("/verify/{username}")
  public ResponseEntity<String> memberVerifyStatus(@PathVariable String username) {
    return ResponseEntity.ok(memberService.findMemberVerifiedStatus(username));
  }

  @GetMapping("/subscription")
  public ResponseEntity<PaginatedRes<GetSubscriptionMemberDetailsRes>> findMemberSubscriptionList(
      @RequestParam Integer page) {
    return ResponseEntity.ok(memberService.findMemberSubscriptionList(page));
  }

  @PostMapping("/subscription")
  public ResponseEntity<Integer> memberSubscription(@RequestBody PostSubscriptionReq req) {
    return ResponseEntity.ok(memberService.addSubscription(req));
  }
}
