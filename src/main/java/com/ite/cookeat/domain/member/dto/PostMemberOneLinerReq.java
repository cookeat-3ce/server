package com.ite.cookeat.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자 한줄 소개 등록 DTO
 *
 * @author 김지수
 * @version 1.0
 * @since 2024.08.24
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.24    김지수       최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PostMemberOneLinerReq {

  public String username;
  public String oneLiner;
}
