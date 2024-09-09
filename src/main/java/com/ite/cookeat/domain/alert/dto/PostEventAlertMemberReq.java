package com.ite.cookeat.domain.alert.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostEventAlertMemberReq {

  private String username;
  private Integer eventId;

  @Setter
  private Integer result;
}
