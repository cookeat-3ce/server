package com.ite.cookeat.domain.member.service;

import static com.ite.cookeat.exception.ErrorCode.MEMBER_NOT_FOUND;
import static com.ite.cookeat.exception.ErrorCode.VERIFYING_FAILED;

import com.ite.cookeat.domain.member.dto.GetMemberNoticeRes;
import com.ite.cookeat.domain.member.dto.GetSubscriptionMemberDetailsRes;
import com.ite.cookeat.domain.member.dto.GetSubscriptionMemberReq;
import com.ite.cookeat.domain.member.dto.GetUserDetailsReq;
import com.ite.cookeat.domain.member.dto.GetUserDetailsRes;
import com.ite.cookeat.domain.member.dto.Member;
import com.ite.cookeat.domain.member.dto.PostLoginReq;
import com.ite.cookeat.domain.member.dto.PostLoginRes;
import com.ite.cookeat.domain.member.dto.PostMemberOneLinerReq;
import com.ite.cookeat.domain.member.dto.PostSignUpReq;
import com.ite.cookeat.domain.member.dto.PostSubscriptionReq;
import com.ite.cookeat.domain.member.dto.TokenDTO;
import com.ite.cookeat.domain.member.mapper.MemberMapper;
import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.exception.ErrorCode;
import com.ite.cookeat.global.dto.Criteria;
import com.ite.cookeat.global.dto.PaginatedRes;
import com.ite.cookeat.security.jwt.JwtTokenProvider;
import com.ite.cookeat.util.SecurityUtils;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 양재혁
 * @version 1.0
 * @since 2024.08.22
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.22    양재혁       최초 생성
 * </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final AuthenticationManager authenticationManager;
  private final BCryptPasswordEncoder passwordEncoder;
  private final MemberMapper memberMapper;
  private final JwtTokenProvider jwtTokenProvider;

  /**
   * 멤버 정보 조회
   *
   * @param username
   * @return 멤버 정보
   */
  @Override
  @Transactional(readOnly = true)
  public GetUserDetailsRes findUserDetailsByUsername(String username) {

    String cur_username = SecurityUtils.getCurrentUsername();

    if (cur_username == null) {
      GetUserDetailsReq req = GetUserDetailsReq.builder()
          .followingname(username)
          .build();
      memberMapper.selectNullUserDetails(req);
      return GetUserDetailsRes.builder()
          .username(req.getUsername())
          .oneLiner(req.getOneLiner())
          .followStatus(req.getFollowStatus())
          .sskcookCount(req.getSskcookCount())
          .subscriptionCount(req.getSubscriptionCount())
          .nickname(req.getNickname())
          .profileImage(req.getProfileImage())
          .build();
    }

    GetUserDetailsReq req = GetUserDetailsReq.builder()
        .followingname(username)
        .followername(cur_username)
        .build();

    memberMapper.selectUserDetails(req);

    return GetUserDetailsRes.builder()
        .username(req.getUsername())
        .oneLiner(req.getOneLiner())
        .followStatus(req.getFollowStatus())
        .sskcookCount(req.getSskcookCount())
        .subscriptionCount(req.getSubscriptionCount())
        .nickname(req.getNickname())
        .profileImage(req.getProfileImage())
        .build();


  }

  /**
   * 회원 가입
   *
   * @param postSignUpReq
   */
  @Override
  @Transactional
  public void addMember(PostSignUpReq postSignUpReq) {
    int cnt = memberMapper.selectDuplicatedUsername(postSignUpReq.getUsername());
    if (cnt == 1) {
      throw new CustomException(ErrorCode.DUPLICATED_MEMBER);
    }
    Member member = Member.builder()
        .username(postSignUpReq.getUsername())
        .password(passwordEncoder.encode(postSignUpReq.getPassword()))
        .nickname(postSignUpReq.getNickname())
        .profileImage(postSignUpReq.getProfileImage())
        .roles("ROLE_USER")
        .build();
    memberMapper.insertMember(member);
  }

  /**
   * 로그인
   *
   * @param postLoginReq
   * @return 로그인 성공한 멤버 정보
   */
  @Override
  @Transactional
  public PostLoginRes login(PostLoginReq postLoginReq) {
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        postLoginReq.getUsername(), postLoginReq.getPassword());
    log.info("auth token: " + authenticationToken.getName() + " "
        + authenticationToken.getCredentials());
    Authentication authentication = authenticationManager.authenticate(authenticationToken);
    Optional<Member> optionalMember = memberMapper.selectUsername(postLoginReq.getUsername());
    System.out.println(optionalMember.get().getProfileImage());
    optionalMember.orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
    TokenDTO tokenDTO = jwtTokenProvider.generateToken(authentication);
    return PostLoginRes.builder()
        .username(optionalMember.get().getUsername())
        .nickname(optionalMember.get().getNickname())
        .profileImage(optionalMember.get().getProfileImage())
        .accessToken(tokenDTO.getAccessToken())
        .build();
  }

  @Override
  @Transactional(readOnly = true)
  public Integer findMemberId(String username) {
    Optional<Integer> result = memberMapper.selectMemberId(username);
    if (result.isEmpty()) {
      throw new CustomException(MEMBER_NOT_FOUND);
    }
    return result.get();
  }

  /**
   * 멤버 검색
   *
   * @param keyword
   * @param page
   * @return 멤버 목록 페이징 데이터
   */
  @Override
  @Transactional
  public PaginatedRes<GetUserDetailsRes> findSearchMember(String keyword, Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .keyword(keyword)
        .build();
    return PaginatedRes.<GetUserDetailsRes>builder()
        .cri(cri)
        .total(memberMapper.selectSearchMemberCount(keyword))
        .data(memberMapper.selectSearchMember(cri, keyword))
        .build();
  }

  @Override
  @Transactional
  public Integer modifyMemberOneLiner(PostMemberOneLinerReq req) {
    Integer result = memberMapper.updateMemberOneLiner(req);
    if (result <= 0) {
      throw new CustomException(MEMBER_NOT_FOUND);
    }
    return result;
  }

  @Override
  @Transactional
  public Integer addSubscription(PostSubscriptionReq req) {
    req.setFollowerUsername(SecurityUtils.getCurrentUsername());
    memberMapper.insertSubscription(req);
    return req.getResult();
  }

  /**
   * 구독한 멤버 목록
   *
   * @param page
   * @return 구독한 멤버 목록 페이징 데이터
   */
  @Override
  @Transactional(readOnly = true)
  public PaginatedRes<GetSubscriptionMemberDetailsRes> findMemberSubscriptionList(Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .build();

    String username = SecurityUtils.getCurrentUsername();

    GetSubscriptionMemberReq req = GetSubscriptionMemberReq.builder()
        .cri(cri)
        .username(username)
        .build();

    memberMapper.selectMemberSubscriptionList(req);

    return PaginatedRes.<GetSubscriptionMemberDetailsRes>builder()
        .cri(cri)
        .total(req.getTotal())
        .data(req.getSubscriptionList())
        .build();
  }

  @Override
  @Transactional
  public void modifyMemberDeletedate(String username) {
    Integer result = memberMapper.updateMemberDeletedate(username);
    if (result <= 0) {
      throw new CustomException(MEMBER_NOT_FOUND);
    }
  }

  @Override
  @Transactional
  public Integer modifyVerifyStatus(String username, String status) {
    Integer result = memberMapper.updateVerifiedStatus(username, status);
    if (result <= 0) {
      throw new CustomException(VERIFYING_FAILED);
    }
    return result;
  }

  @Override
  @Transactional
  public String findMemberVerifiedStatus(String username) {
    String result = memberMapper.selectMemberVerifiedStatus(username);
    if (result == null) {
      throw new CustomException(MEMBER_NOT_FOUND);
    }
    return result;
  }

  @Override
  @Transactional(readOnly = true)
  public PaginatedRes<GetMemberNoticeRes> findMemberNotices(String username, Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .build();

    return PaginatedRes.<GetMemberNoticeRes>builder()
        .cri(cri)
        .total(memberMapper.selectMemberNoticeCount(username))
        .data(memberMapper.selectMemberNotices(cri, username))
        .build();
  }
}
