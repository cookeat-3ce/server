package com.ite.cookeat.domain.longcook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetLongcookDetailsRes {

  private Integer longcookId;
  private String longcookUrl;
  private String recipe;
  private String title;
  private String username;

}
