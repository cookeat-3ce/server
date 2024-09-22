package com.ite.cookeat.domain.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 신고된 슥쿡 영상 BLOCKED 요청 DTO
 *
 * @author 김지수
 * @version 1.0
 * @since 2024.08.26
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.26    김지수       최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class DeleteReportSskcookReq {

  @Setter
  private Integer sskcookId;

  @Setter
  private Integer result;
}
