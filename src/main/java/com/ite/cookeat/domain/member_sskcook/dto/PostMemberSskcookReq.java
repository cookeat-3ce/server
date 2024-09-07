package com.ite.cookeat.domain.member_sskcook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PostMemberSskcookReq {

  @Setter
  private Integer memberId;
  private Integer sskcookId;
}
