package com.ite.cookeat.domain.longcook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostLongcookReq {

  private String username;
  private String title;
  private Integer longcookId;

  @Setter
  private String longcookUrl;
}
