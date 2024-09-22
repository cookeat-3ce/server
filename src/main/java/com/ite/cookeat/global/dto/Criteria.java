package com.ite.cookeat.global.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 데이터 조회 시 페이지네이션, 필터링에 관련된 정보 DTO
 *
 * @author 김지수
 * @version 1.0
 * @since 2024.08.23
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.23    김지수       최초 생성
 * </pre>
 */
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

  private String tag;
}
