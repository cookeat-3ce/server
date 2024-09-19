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
  private String username;
  private String profileImage;
  private Integer people;
  private String title;
  private String thumbnail;
  private String sessionId;
  private Integer type;
}
