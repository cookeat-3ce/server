package com.ite.cookeat.domain.member_sskcook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PostMemberSskcookReq {

  private Integer memberId;
  private Integer sskcookId;
}
