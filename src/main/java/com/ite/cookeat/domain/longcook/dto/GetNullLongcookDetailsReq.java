package com.ite.cookeat.domain.longcook.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GetNullLongcookDetailsReq {

  private Integer longcookId;

  @Setter
  private List<GetLongcookIngredientsRes> ingredients;

  @Setter
  private List<GetLongcookDetailsRes> details;
}
