package com.ite.cookeat.domain.sskcook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PostLikesReq {

  private Integer sskcookId;

  @Setter
  private Integer memberId;
  @Setter
  private String action;
}
