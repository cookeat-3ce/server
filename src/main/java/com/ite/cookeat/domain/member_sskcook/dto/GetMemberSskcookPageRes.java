package com.ite.cookeat.domain.member_sskcook.dto;

import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GetMemberSskcookPageRes {

  // 이전, 이후 값이 있는 지 없는 지
  private boolean prev, next;

  // 데이터 총 개수
  private Integer total;
  // 페이지 정보
  private Criteria cri;
  // 공지 리스트
  private List<GetMemberSskcookRes> memberSskcooks;

  @Builder
  public GetMemberSskcookPageRes(Integer total, Criteria cri,
      List<GetMemberSskcookRes> memberSskcooks) {
    this.cri = cri;
    this.total = total;
    this.memberSskcooks = memberSskcooks;

    int pageSize = cri.getPageSize();
    int pageNum = cri.getPageNum();

    int realEnd = (int) (Math.ceil(total * 1.0 / pageSize));

    this.prev = pageNum > 1;
    this.next = pageNum < realEnd;
  }
}
