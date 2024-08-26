package com.ite.cookeat.domain.live.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetLiveRes {

  private Integer liveId;
  private String nickname;
  private Integer people;
  private String title;

}
