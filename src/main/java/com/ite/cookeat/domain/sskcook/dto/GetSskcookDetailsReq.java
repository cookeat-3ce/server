package com.ite.cookeat.domain.sskcook.dto;

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
public class GetSskcookDetailsReq {

  private String username;
  private Integer sskcookId;

  @Setter
  private List<String> tags;

  @Setter
  private List<GetSskcookIngredientsRes> ingredients;

  @Setter
  private List<GetSskcookDetailsRes> details;
}
