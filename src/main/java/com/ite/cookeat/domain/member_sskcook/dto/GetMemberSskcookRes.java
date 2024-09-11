package com.ite.cookeat.domain.member_sskcook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GetMemberSskcookRes {

  private Integer sskcookId;
  private String nickname;
  private String sskcookUrl;
  private String title;
  private Integer countLikes;
  private String profileImage;
}
