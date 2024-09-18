package com.ite.cookeat.domain.admin.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ite.cookeat.util.KSTDateSerializer;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetTopSskcookRes {

  private Integer sskcookId;
  private Integer likeCounts;
  private String yearMonth;
  private String profileImage;
  @JsonSerialize(using = KSTDateSerializer.class)
  private Date regdate;
  private String sskcookUrl;
  private String title;
  private String nickname;
  private String username;
}
