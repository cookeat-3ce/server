package com.ite.cookeat.security.jwt;

import com.ite.cookeat.domain.member.dto.Member;
import com.ite.cookeat.domain.member.dto.TokenDTO;
import com.ite.cookeat.security.PrincipalDetails;
import com.ite.cookeat.security.PrincipalDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
@Component
@Slf4j
/**
 * JWT 생성
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

public class JwtTokenProvider implements InitializingBean {

  private static final String AUTHORITIES_KEY = "auth";
  private static final String PREFIX = "Bearer";
  private static long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 120; // 120분
  private final PrincipalDetailsService principalDetailsService;
  @Value("${jwt.secret}")
  private String secret;
  private Key key;

  @Override
  public void afterPropertiesSet() throws Exception {
    byte[] encodedKey = Base64.getEncoder().encode(secret.getBytes());
    this.key = Keys.hmacShaKeyFor(encodedKey);
  }


  public String generateAccessToken(Member member, long now) {
    return Jwts.builder()
        .setSubject(member.getUsername())
        .claim(AUTHORITIES_KEY, member.getRoles())
        .setExpiration(new Date(now + ACCESS_TOKEN_EXPIRE_TIME))
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  public TokenDTO generateToken(Authentication authentication) {
    log.info("[jwt token provider] generate token: " + authentication.getPrincipal());

    log.info("authentication: " + authentication.getName());

    long now = new Date().getTime();
    String accessToken = generateAccessToken(
        ((PrincipalDetails) authentication.getPrincipal()).getMember(), now);

    return TokenDTO.builder()
        .accessToken(accessToken)
        .build();
  }

  public Authentication getAuthentication(String accessToken) {

    Claims claims = parseClaims(accessToken);

//    if (claims.get(AUTHORITIES_KEY) == null) throw new CustomException(ErrorCode.INVALID_AUTH_TOKEN);

    UserDetails userDetails = principalDetailsService.loadUserByUsername(claims.getSubject());

    return new UsernamePasswordAuthenticationToken(userDetails, accessToken,
        userDetails.getAuthorities());
  }

  public boolean validateToken(String token) {
    try {

      Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(token);

      return true;
    } catch (SecurityException | MalformedJwtException e) {
      log.error("유효하지 않은 access 토큰입니다.");
      throw new JwtException("유효하지 않은 access 토큰입니다.");
    } catch (ExpiredJwtException e) {
      log.error("만료된 access 토큰입니다.");
      throw new JwtException("만료된 access 토큰입니다.");
    } catch (UnsupportedJwtException e) {
      log.error("지원되지 않는 access 토큰입니다.");
      throw new JwtException("지원되지 않는 access 토큰입니다.");
    } catch (IllegalArgumentException e) {
      log.error("잘못된 access 토큰입니다.");
      throw new JwtException("잘못된 access 토큰입니다.");
    }
  }

  private Claims parseClaims(String accessToken) {
    try {
      return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
    } catch (ExpiredJwtException e) {
      return e.getClaims();
    }
  }
}