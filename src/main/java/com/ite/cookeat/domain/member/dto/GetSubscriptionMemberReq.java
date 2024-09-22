package com.ite.cookeat.domain.member.dto;

import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 사용자 구독 목록 조회 DTO
 *
 * @author 김지수
 * @version 1.0
 * @since 2024.08.29
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