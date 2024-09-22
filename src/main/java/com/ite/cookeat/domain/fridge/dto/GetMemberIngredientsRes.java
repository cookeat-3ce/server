package com.ite.cookeat.domain.fridge.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ite.cookeat.util.KSTDateSerializer;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 식재료 조회 DTO
 *
 * @author 김지수
 * @version 1.0
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.23    김지수       최초 생성
 * 2024.09.07    김지수       유효기간 데이터 추가
 * </pre>
 * @since 2024.08.23
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetMemberIngredientsRes {

  private Integer ingredientId;
  private String name;
  private String amount;
  private String icon;
  @JsonSerialize(using = KSTDateSerializer.class)
  private Date expdate;
}
