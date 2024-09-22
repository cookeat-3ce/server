package com.ite.cookeat.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자 공지 목록 조회 DTO
 *
 * @author 김지수
 * @version 1.0
 * @since 2024.08.23
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
public class GetMemberNoticeRes {

  private Integer noticeId;
  private String title;
  private String content;
}
