package com.ite.cookeat.domain.longcook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GetLongcookRes {

  private Integer longcookId;
  private String longcookUrl;
  private String title;
  private String regdate;

}
