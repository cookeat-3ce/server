package com.ite.cookeat.domain.fridge.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ite.cookeat.util.KSTDateSerializer;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
