package com.ite.cookeat.util;

import com.ite.cookeat.security.PrincipalDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Spring Security의 인증 컨텍스트에서 현재 인증된 사용자의 정보 조회
 *
 * @author 김지수
 * @version 1.0
 * @since 2024.08.29
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.29    김지수       최초 생성
 * </pre>
 */
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
