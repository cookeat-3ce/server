package com.ite.cookeat.domain.longcook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 스윽쿡 상세 조회를 위한 DTO
 *
 * @author 박유진
 * @version 1.0
 * @since 2024.09.02
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.09.02    박유진        최초 생성, 비로그인 상태에서 스윽쿡 조회 가능하도록 수정
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetLongcookDetailsRes {

  private Integer longcookId;
  private String longcookUrl;
  private String recipe;
  private String title;
  private String username;

}
