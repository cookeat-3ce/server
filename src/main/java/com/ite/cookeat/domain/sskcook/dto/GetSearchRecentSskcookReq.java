package com.ite.cookeat.domain.sskcook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GetSearchRecentSskcookReq {

  private String keyword;
  private Integer page;

  @Builder.Default
  private Integer pageSize = 10;
}
