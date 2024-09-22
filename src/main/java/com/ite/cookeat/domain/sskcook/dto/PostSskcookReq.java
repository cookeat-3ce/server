package com.ite.cookeat.domain.sskcook.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 슥쿡 등록을 위한 DTO
 *
 * @author 박유진
 * @version 1.0
 * @since 2024.08.24
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.24    박유진       최초 생성
 * 2024.08.26    박유진       memberId 대신 username을 넘기도록 수정
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostSskcookReq {

  private String username;
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
  private Integer memberId;
}
