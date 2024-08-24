package com.ite.cookeat.domain.fridge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetIngredientsRes {

  private String name;
  private String amount;
  private String icon;
}
