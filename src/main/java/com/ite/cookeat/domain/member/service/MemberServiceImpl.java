package com.ite.cookeat.domain.member.service;

import com.ite.cookeat.domain.member.dto.GetUserDetailsRes;
import com.ite.cookeat.domain.member.mapper.MemberMapper;
import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberMapper memberMapper;

  @Override
  @Transactional(readOnly = true)
  public GetUserDetailsRes findUserDetailsByUsername(String username) {

    GetUserDetailsRes getUserDetailsRes = memberMapper.selectUserDetails(username);

    if (getUserDetailsRes == null) {
      throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
    }

    return getUserDetailsRes;
  }
}
