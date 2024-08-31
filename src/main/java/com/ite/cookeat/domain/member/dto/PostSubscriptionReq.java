package com.ite.cookeat.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PostSubscriptionReq {

  @Setter
  private Integer subscriptionId;
  // 팔로워
  private String followerUsername;
  // 팔로잉
  private String followingUsername;
}
