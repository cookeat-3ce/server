package com.ite.cookeat.domain.alert.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostEventAlertMemberReq {

  @Setter
  private Integer alertId;
  private String username;
  private Integer eventId;
}
