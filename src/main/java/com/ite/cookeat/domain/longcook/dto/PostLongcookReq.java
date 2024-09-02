package com.ite.cookeat.domain.longcook.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostLongcookReq {

  private String username;
  private String title;
  private String recipe;
  private List<PostLongcookIngredientReq> ingredient;
  private Integer longcookId;

  @Setter
  private String longcookUrl;
  @Setter
  private String ingredientsJson;
  @Setter
  private Integer memberId;
}
