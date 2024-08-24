package com.ite.cookeat.domain.longcook.dto;

import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GetLongCookPageRes {

  private boolean prev, next;

  // 데이터 총 개수
  private Integer total;
  // 페이지 정보
  private Criteria cri;

  private List<GetLongcookRes> longcooks;

  @Builder
  GetLongCookPageRes(Integer total, Criteria cri, List<GetLongcookRes> longcooks) {
    this.total = total;
    this.cri = cri;
    this.longcooks = longcooks;

    int pageSize = cri.getPageSize();
    int pageNum = cri.getPageNum();

    int realEnd = (int) (Math.ceil(total * 1.0 / pageSize));

    this.prev = pageNum > 1;
    this.next = pageNum < realEnd;
  }

}
