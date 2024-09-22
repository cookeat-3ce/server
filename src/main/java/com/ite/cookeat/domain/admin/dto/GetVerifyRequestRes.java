package com.ite.cookeat.domain.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자 인증 요청 조회 DTO
 *
 * @author 김지수
 * @version 1.0
 * @since 2024.09.07
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.09.07    김지수       최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetVerifyRequestRes {

  private Integer memberId;
  private String username;
  private Integer sskcookCount;
  private Integer followerCount;
}
