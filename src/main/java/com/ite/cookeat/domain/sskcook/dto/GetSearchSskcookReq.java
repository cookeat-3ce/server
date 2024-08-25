package com.ite.cookeat.domain.sskcook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GetSearchSskcookReq {

  private String keyword;
  private Integer page;
  private String sort;
  private String date;

  @Builder.Default
  private Integer pageSize = 10;
}
