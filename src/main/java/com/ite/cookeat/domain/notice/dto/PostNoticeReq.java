package com.ite.cookeat.domain.notice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PostNoticeReq {

  @Setter
  private Integer noticeId;
  @Setter
  private Integer memberId;
  private String username;
  private String title;
  private String content;
}
