package com.ite.cookeat.domain.fridge.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ite.cookeat.util.KSTDateSerializer;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 식재료 등록 DTO
 *
 * @author 김지수
 * @version 1.0
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.24    김지수       최초 생성
 * 2024.09.02    박유진       유효기간 데이터 추가
 * </pre>
 * @since 2024.08.23
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostMemberIngredientReq {

  @Setter
  private Integer ingredientId;
  @Setter
  private Integer memberId;
  private String username;
  private String name;
  private String amount;
  private Integer icon;
  @JsonSerialize(using = KSTDateSerializer.class)
  private Date expdate;
}
