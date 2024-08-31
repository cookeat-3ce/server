package com.ite.cookeat.domain.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class DeleteReportSskcookReq {

  @Setter
  private Integer sskcookId;

  @Setter
  private Integer result;
}
