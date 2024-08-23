package com.ite.cookeat.domain.live.dto;

import java.util.Date;
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
  private String thumbnail;
  private String sessionId;

  @Override
  public String toString() {
    return "liveId=" + liveId + ", memberId=" + memberId + ", username=" + username + ", title="
        + title;
  }
}
