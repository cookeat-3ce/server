package com.ite.cookeat.domain.alert.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * SSE 알림 전송 DTO
 *
 * @author 김지수
 * @version 1.0
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.09.11    김지수       최초 생성
 * </pre>
 * @since 2024.09.11
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostEventAlertReq {

  private Integer eventId;
  private String message;
}
