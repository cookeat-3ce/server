package com.ite.cookeat.domain.fridge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostIngredientReq {

  @Setter
  private Integer ingredientId;
  @Setter
  private Integer memberId;
  private String username;
  private String name;
  private String amount;
  private Integer icon;
}
