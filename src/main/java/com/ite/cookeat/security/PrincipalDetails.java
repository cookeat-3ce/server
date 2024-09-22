package com.ite.cookeat.security;

import com.ite.cookeat.domain.member.dto.Member;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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

@RequiredArgsConstructor
@Getter
public class PrincipalDetails implements UserDetails {

  private final Member member;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> authorities = new ArrayList();
    member.getRoleList().forEach((role) -> {
      authorities.add(() -> role);
    });
    return authorities;
  }

  @Override
  public String getPassword() {
    return member.getPassword();
  }

  @Override
  public String getUsername() {
    return member.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}