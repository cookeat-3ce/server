package com.ite.cookeat.domain.sskcook.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 슥쿡 수정을 위한 DTO
 *
 * @author 박유진
 * @version 1.0
 * @since 2024.08.27
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.27    박유진       최초 생성
 * </pre>
 */
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
