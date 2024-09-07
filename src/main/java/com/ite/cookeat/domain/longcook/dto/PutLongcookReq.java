package com.ite.cookeat.domain.longcook.dto;

import com.ite.cookeat.domain.sskcook.dto.PostSskcookIngredientReq;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PutLongcookReq {

  private String title;
  private String recipe;
  private List<PostSskcookIngredientReq> ingredient;
  private Integer longcookId;

  @Setter
  private String longcookUrl;
  @Setter
  private String ingredientsJson;
  @Setter
  private Integer updatedCount;

}
