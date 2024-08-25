package com.ite.cookeat.domain.member.dto;

import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetMemberNoticePageRes {
  // 이전, 이후 값이 있는 지 없는 지
  private boolean prev, next;

  // 데이터 총 개수
  private Integer total;
  // 페이지 정보
  private Criteria cri;
  // 공지 리스트
  private List<GetMemberNoticeRes> notices;

  @Builder
  public GetMemberNoticePageRes(Integer total, Criteria cri, List<GetMemberNoticeRes> notices) {
    this.cri = cri;
    this.total = total;
    this.notices = notices;

    int pageSize = cri.getPageSize();
    int pageNum = cri.getPageNum();

    int realEnd = (int) (Math.ceil(total * 1.0 / pageSize));

    this.prev = pageNum > 1;
    this.next = pageNum < realEnd;
  }
}
