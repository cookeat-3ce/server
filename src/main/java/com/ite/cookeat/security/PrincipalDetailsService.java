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

/**
 * spring security에서 사용되는 사용자 인터페이스(UserDetails)를 구현한 클래스
 *
 * @author 양재혁
 * @version 1.0
 * @since 2024.08.21
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.21    양재혁       최초 생성
 * </pre>
 */
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
