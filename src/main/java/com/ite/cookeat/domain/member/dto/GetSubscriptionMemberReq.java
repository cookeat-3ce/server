package com.ite.cookeat.domain.member.dto;

import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GetSubscriptionMemberReq {

  private Criteria cri;
  private String username;

  @Setter
  private Integer total;

  @Setter
  private List<GetSubscriptionMemberDetailsRes> subscriptionList;
}