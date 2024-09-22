package com.ite.cookeat.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 로그인이 성공적으로 되었다면, 넘길 토큰 클래스
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
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Builder
public class TokenDTO {

  private String accessToken;
}
