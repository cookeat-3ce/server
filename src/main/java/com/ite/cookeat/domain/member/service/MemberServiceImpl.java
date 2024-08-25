package com.ite.cookeat.domain.member.service;

import static com.ite.cookeat.exception.ErrorCode.MEMBER_NOT_FOUND;

import com.ite.cookeat.domain.member.dto.GetMemberNoticePageRes;
import com.ite.cookeat.domain.member.dto.GetUserDetailsRes;
import com.ite.cookeat.domain.member.dto.Member;
import com.ite.cookeat.domain.member.dto.PostLoginReq;
import com.ite.cookeat.domain.member.dto.PostLoginRes;
import com.ite.cookeat.domain.member.dto.PostMemberOneLinerReq;
import com.ite.cookeat.domain.member.dto.PostSignUpReq;
import com.ite.cookeat.domain.member.dto.TokenDTO;
import com.ite.cookeat.domain.member.mapper.MemberMapper;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookReq;
import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.exception.ErrorCode;
import com.ite.cookeat.global.dto.Criteria;
import com.ite.cookeat.security.jwt.JwtTokenProvider;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final AuthenticationManager authenticationManager;
  private final BCryptPasswordEncoder passwordEncoder;
  private final MemberMapper memberMapper;
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  @Transactional(readOnly = true)
  public GetUserDetailsRes findUserDetailsByUsername(String username) {

    GetUserDetailsRes getUserDetailsRes = memberMapper.selectUserDetails(username);

    if (getUserDetailsRes == null) {
      throw new CustomException(MEMBER_NOT_FOUND);
    }

    return getUserDetailsRes;
  }

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
  public Integer findMemberId(String username) {
    Optional<Integer> result = memberMapper.selectMemberId(username);
    if (result.isEmpty()) {
      throw new CustomException(MEMBER_NOT_FOUND);
    }
    return result.get();
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
  @Transactional(readOnly = true)
  public List<GetUserDetailsRes> findSearchMember(GetSearchSskcookReq getSearchSskcookReq) {
    return memberMapper.selectSearchMember(getSearchSskcookReq);
  }

  @Override
  public GetMemberNoticePageRes findMemberNotices(String username, Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .build();

    return GetMemberNoticePageRes.builder()
        .cri(cri)
        .total(memberMapper.selectMemberNoticeCount(username))
        .notices(memberMapper.selectMemberNotices(cri, username))
        .build();
  }
}
