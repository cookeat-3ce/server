package com.ite.cookeat.domain.longcook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GetLongcookIngredientsRes {
  private String name;
  private String amount;
}
