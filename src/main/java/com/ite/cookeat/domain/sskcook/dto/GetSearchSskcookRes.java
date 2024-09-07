package com.ite.cookeat.domain.sskcook.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ite.cookeat.util.KSTDateSerializer;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GetSearchSskcookRes {

  private Integer sskcookId;
  private String nickname;
  private String sskcookUrl;
  private String title;
  @JsonSerialize(using = KSTDateSerializer.class)
  private Date regdate;
  private Integer countLikes;
}
