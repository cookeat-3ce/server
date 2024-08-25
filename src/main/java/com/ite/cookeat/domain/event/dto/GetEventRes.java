package com.ite.cookeat.domain.event.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ite.cookeat.util.KSTDateSerializer;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetEventRes {

  private Integer eventId;
  private String title;
  @JsonSerialize(using = KSTDateSerializer.class)
  private Date startdate;
  @JsonSerialize(using = KSTDateSerializer.class)
  private Date enddate;
  private String thumbnail;
  @JsonSerialize(using = KSTDateSerializer.class)
  private Date regdate;
}
