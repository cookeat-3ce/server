package com.ite.cookeat.domain.longcook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PutLongcookReq {

  private Integer longcookId;
  private String title;
  @Setter
  private String longcookUrl;

}
