package com.ite.cookeat.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 구독 성공 시 응답 DTO
 *
 * @author 양재혁
 * @version 1.0
 * @since 2024.08.25
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.25    양재혁       최초 생성
 * </pre>
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class GetSubscriptionMemberDetailsRes {

  private String username;
  private String nickname;
  private String profileImage;
}
