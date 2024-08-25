package com.ite.cookeat.domain.live.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GetLivePageRes {

  private Integer liveId;
  private String nickname;
  private Integer people;

}
