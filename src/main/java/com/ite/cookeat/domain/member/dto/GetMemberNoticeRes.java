package com.ite.cookeat.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GetMemberNoticeRes {

  private Integer noticeId;
  private String title;
  private String content;
}
