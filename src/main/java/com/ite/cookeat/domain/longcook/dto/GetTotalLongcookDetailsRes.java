package com.ite.cookeat.domain.longcook.dto;

import com.ite.cookeat.domain.sskcook.dto.GetSskcookDetailsRes;
import com.ite.cookeat.domain.sskcook.dto.GetSskcookIngredientsRes;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class GetTotalLongcookDetailsRes {
  private List<GetLongcookIngredientsRes> ingredients;
  private List<GetLongcookDetailsRes> details;

}
