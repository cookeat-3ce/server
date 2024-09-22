package com.ite.cookeat.global.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 페이징된 데이터 조회 DTO
 *
 * @author 김지수
 * @version 1.0
 * @since 2024.08.26
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.26    김지수       최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaginatedRes<T> {

  // 이전, 이후 값이 있는 지 없는 지
  private boolean prev, next;

  // 데이터 총 개수
  private Integer total;
  // 페이지 정보
  private Criteria cri;
  // 리스트
  private List<T> data;

  @Builder
  public PaginatedRes(Integer total, Criteria cri, List<T> data) {
    this.cri = cri;
    this.total = total;
    this.data = data;

    int pageSize = cri.getPageSize();
    int pageNum = cri.getPageNum();

    int realEnd = (int) (Math.ceil(total * 1.0 / pageSize));

    this.prev = pageNum > 1;
    this.next = pageNum < realEnd;
  }
}
