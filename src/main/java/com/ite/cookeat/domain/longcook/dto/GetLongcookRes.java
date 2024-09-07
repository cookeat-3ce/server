package com.ite.cookeat.domain.longcook.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ite.cookeat.util.KSTDateSerializer;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class GetLongcookRes {

  private Integer longcookId;
  private String username;
  private String nickname;
  private String title;
  private String longcookUrl;
  @JsonSerialize(using = KSTDateSerializer.class)
  private Date regdate;
  private String profileImage;
}
