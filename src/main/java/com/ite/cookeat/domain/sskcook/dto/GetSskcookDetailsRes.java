package com.ite.cookeat.domain.sskcook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GetSskcookDetailsRes {

  private Integer sskcookId;
  private String sskcookUrl;
  private String recipe;
  private String title;
  private String reportStatus;
  private String likeStatus;
  private String storeStatus;
  private String followStatus;
  private String nickname;
  private String username;
  private String profileImage;
  private Integer likeCount;
}
