package com.ite.cookeat.util;

import com.ite.cookeat.security.PrincipalDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

  /**
   * 현재 인증된 사용자(authentication.principal)의 PrincipalDetails를 반환
   *
   * @return 현재 인증된 사용자의 PrincipalDetails, 인증되지 않은 경우 null
   */
  public static PrincipalDetails getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.getPrincipal() instanceof PrincipalDetails) {
      return (PrincipalDetails) authentication.getPrincipal();
    }
    return null;
  }

  /**
   * 현재 인증된 사용자(authentication.principal)의 username을 반환
   *
   * @return 현재 인증된 사용자의 username, 인증되지 않은 경우 null
   */
  public static String getCurrentUsername() {
    PrincipalDetails principalDetails = getCurrentUser();
    return (principalDetails != null) ? principalDetails.getUsername() : null;
  }
}
