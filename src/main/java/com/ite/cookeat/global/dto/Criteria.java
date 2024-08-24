package com.ite.cookeat.global.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Criteria {

  // 한 페이지에 들어갈 데이터 크기
  private Integer pageSize;
  // 몇번째 페이지인지
  private Integer pageNum;

  // filtering 관련
  private String type;
  // 검색 시 사용되는 키워드
  private String keyword;
  
  private String date;
}
