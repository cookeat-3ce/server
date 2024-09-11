package com.ite.cookeat.domain.alert.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostEventAlertReq {

  private Integer eventId;
  private String message;
}
