package com.ite.cookeat.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GetUserDetailsReq {

  private String followingname;
  private String followername;

  @Setter
  private String username;
  @Setter
  private String nickname;
  @Setter
  private String profileImage;
  @Setter
  private String oneLiner;
  @Setter
  private Integer subscriptionCount;
  @Setter
  private Integer sskcookCount;
  @Setter
  private String followStatus;

}
