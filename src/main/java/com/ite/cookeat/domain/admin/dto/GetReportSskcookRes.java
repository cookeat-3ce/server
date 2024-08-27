package com.ite.cookeat.domain.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetReportSskcookRes {

  private Integer sskcookId;
  private String title;
  //  private String username;
//  private String memberId;
  private Integer reportCount;
}
