package com.ite.cookeat.domain.sskcook.dto;

import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetSearchSskcookPageRes {

  // 이전, 이후 값이 있는 지 없는 지
  private boolean prev, next;

  // 데이터 총 개수
  private Integer total;
  // 페이지 정보
  private Criteria cri;
  // 검색 멤버 리스트
  private List<GetSearchSskcookRes> sskcooks;

  @Builder
  GetSearchSskcookPageRes(Integer total, Criteria cri, List<GetSearchSskcookRes> sskcooks) {
    this.total = total;
    this.cri = cri;
    this.sskcooks = sskcooks;

    int pageSize = cri.getPageSize();
    int pageNum = cri.getPageNum();

    int realEnd = (int) (Math.ceil(total * 1.0 / pageSize));

    this.prev = pageNum > 1;
    this.next = pageNum < realEnd;
  }
}
