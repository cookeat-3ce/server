package com.ite.cookeat.domain.alert.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 특정 이벤트 등록한 사용자 SSE 등록 DTO
 *
 * @author 김지수
 * @version 1.0
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.26    김지수       최초 생성
 * 2024.09.09    김지수       StoredProcedure 적용
 * </pre>
 * @since 2024.08.26
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostEventAlertMemberReq {

  private String username;
  private Integer eventId;

  @Setter
  private Integer result;
}
