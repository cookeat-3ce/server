package com.ite.cookeat.domain.sskcook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostHashtagReq {

  private Integer hashtagId;
  private String name;
  @Setter
  private Integer sskcookId;
}
