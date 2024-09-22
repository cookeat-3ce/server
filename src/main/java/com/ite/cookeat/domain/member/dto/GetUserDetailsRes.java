package com.ite.cookeat.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 멤버 조회 성공 시 DTO
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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GetUserDetailsRes {

  private String username;
  private String nickname;
  private String profileImage;
  private String oneLiner;
  private Integer subscriptionCount;
  private Integer sskcookCount;
  private String followStatus;
}
