package com.ite.cookeat.domain.live.dto;

import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetLivePageRes {

  private boolean prev, next;
  private Integer total;
  private Criteria cri;
  private List<GetLiveRes> lives;

  @Builder
  public GetLivePageRes(Integer total, Criteria cri, List<GetLiveRes> lives) {
    this.cri = cri;
    this.total = total;
    this.lives = lives;

    int pageSize = cri.getPageSize();
    int pageNum = cri.getPageNum();

    int realEnd = (int) (Math.ceil(total * 1.0 / pageSize));

    this.prev = pageNum > 1;
    this.next = pageNum < realEnd;
  }

}
