package com.ite.cookeat.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 사용자 구독 DTO
 *
 * @author 김지수
 * @version 1.0
 * @since 2024.08.25
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.25    김지수       최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PostSubscriptionReq {

  @Setter
  private Integer result;
  // 팔로워
  @Setter
  private String followerUsername;
  // 팔로잉
  private String followingUsername;
}
