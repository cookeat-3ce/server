package com.ite.cookeat.domain.admin.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostEventReq {

  @Setter
  private Integer eventId;
  private String title;
  private String content;
  private Date startdate;
  private Date enddate;
  private String thumbnail;
}
