package com.ite.cookeat.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * 로그 아웃 핸들러
 *
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
public class CustomLogoutHandler implements LogoutSuccessHandler {

  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().flush();
  }
}
