package com.ite.cookeat.domain.live.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 실시간 요리 클래스 조회 DTO
 *
 * @author 김지수
 * @version 1.0
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.25    박유진       최초 생성
 * 2024.09.10    김지수       클래스 조회 시 thumbnail, sessionId, type 데이터 추가
 * </pre>
 * @since 2024.08.25
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetLiveRes {

  private Integer liveId;
  private String nickname;
  private String username;
  private String profileImage;
  private Integer people;
  private String title;
  private String thumbnail;
  private String sessionId;
  private Integer type;
}
