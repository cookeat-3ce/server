package com.ite.cookeat.domain.sskcook.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PutSskcookReq {

  private String title;
  private String recipe;
  private List<PostHashtagReq> hashtag;
  private List<PostSskcookIngredientReq> ingredient;
  private Integer sskcookId;

  @Setter
  private String sskcookUrl;
  @Setter
  private String ingredientsJson;
  @Setter
  private String hashtagsJson;
  @Setter
  private Integer updatedCount;
}
