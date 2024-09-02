package com.ite.cookeat.domain.longcook.dto;

import com.ite.cookeat.domain.sskcook.dto.GetSskcookDetailsRes;
import com.ite.cookeat.domain.sskcook.dto.GetSskcookIngredientsRes;
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
public class GetLongcookDetailsReq {
  private String username;
  private Integer longcookId;

  @Setter
  private List<GetLongcookIngredientsRes> ingredients;

  @Setter
  private List<GetLongcookDetailsRes> details;
}
