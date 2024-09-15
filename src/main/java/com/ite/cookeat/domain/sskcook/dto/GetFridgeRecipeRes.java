package com.ite.cookeat.domain.sskcook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetFridgeRecipeRes {
  private Integer sskcookId;
  private String sskcookUrl;
  private String profileImage;
  private String nickname;
  private String title;
  private String username;
}