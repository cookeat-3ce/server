package com.ite.cookeat.domain.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetTopSskcookRes {

  private Integer sskcookId;
  private Integer likeCounts;
  private String yearMonth;
}
