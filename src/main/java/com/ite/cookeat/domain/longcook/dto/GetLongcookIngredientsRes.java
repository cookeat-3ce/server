package com.ite.cookeat.domain.longcook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
 * 2024.09.02    박유진       최초 생성, 스윽쿡 업로드 시 레시피, 재료도 추가
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GetLongcookIngredientsRes {
  private String name;
  private String amount;
}
