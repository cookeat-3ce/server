package com.ite.cookeat.domain.longcook.dto;

import com.ite.cookeat.domain.sskcook.dto.PostSskcookIngredientReq;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 스윽쿡 수정을 위한 DTO
 *
 * @author 박유진
 * @version 1.0
 * @since 2024.09.02
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.26    박유진       최초 생성, 스윽쿡 수정
 * 2024.09.02    박유진       스윽쿡 업데이트 시 레시피, 재료도 추가
 * </pre>
 */
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
