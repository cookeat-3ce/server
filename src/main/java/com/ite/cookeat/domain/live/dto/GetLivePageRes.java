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
@Builder
public class GetLivePageRes {

  private boolean prev, next;
  private Integer total;
  private Criteria cri;
  private List<GetLiveRes> lives;

}
