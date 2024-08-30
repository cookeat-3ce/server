package com.ite.cookeat.security;

import static com.ite.cookeat.exception.ErrorCode.MEMBER_NOT_FOUND;

import com.ite.cookeat.domain.member.dto.Member;
import com.ite.cookeat.domain.member.mapper.MemberMapper;
import com.ite.cookeat.exception.CustomException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

  @Autowired
  private MemberMapper memberMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Member> optional_member = memberMapper.selectUsername(username);

    optional_member.orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

    log.info("[PrincipalDetailsService] loadUserByUsername {{}}", optional_member.get());

    return new PrincipalDetails(optional_member.get());
  }
}
