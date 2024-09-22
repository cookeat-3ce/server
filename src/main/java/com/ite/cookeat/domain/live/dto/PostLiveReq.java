package com.ite.cookeat.domain.live.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 실시간 요리 클래스 등록 DTO
 *
 * @author 김지수
 * @version 1.0
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.23    김지수       최초 생성
 * 2024.09.04    김지수       클래스 등록 시 type 데이터 추가
 * </pre>
 * @since 2024.08.23
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PostLiveReq {

  @Setter
  private Integer liveId;

  @Setter
  private Integer memberId;
  private String username;
  private String title;
  private Integer people;
  @Setter
  private String thumbnail;
  private String sessionId;
  private Integer type;
}
