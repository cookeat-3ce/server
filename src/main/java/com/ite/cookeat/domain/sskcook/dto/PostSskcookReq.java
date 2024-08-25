package com.ite.cookeat.domain.sskcook.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostSskcookReq {

  private Integer memberId;
  private String title;
  private String recipe;
  private List<PostHashtagReq> hashtag;
  private List<PostIngredientReq> ingredient;
  private Integer sskcookId;

  @Setter
  private String sskcookUrl;
}
