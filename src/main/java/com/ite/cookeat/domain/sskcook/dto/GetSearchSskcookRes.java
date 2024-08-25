package com.ite.cookeat.domain.sskcook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GetSearchSskcookRes {
  private Integer sskcookId;
  private String nickname;
  private String sskcookUrl;
  private String title;
  private String regdate;
  private Integer countLikes;
}
