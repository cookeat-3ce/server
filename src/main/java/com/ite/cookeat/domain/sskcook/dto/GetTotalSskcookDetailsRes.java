package com.ite.cookeat.domain.sskcook.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class GetTotalSskcookDetailsRes {

  private List<String> tags;
  private List<GetSskcookIngredientsRes> ingredients;
  private List<GetSskcookDetailsRes> details;
}
