package com.ite.cookeat.domain.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetVerifyRequestRes {

  private Integer memberId;
  private String username;
  private Integer sskcookCount;
  private Integer followerCount;
}
