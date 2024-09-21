package com.ite.cookeat.domain.live.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PostLiveReq {

  @Setter
  private Integer liveId;

  @Setter
  private Integer memberId;
  private String username;
  private String title;
  private Integer people;
  @Setter
  private String thumbnail;
  private String sessionId;
  private Integer type;
}
