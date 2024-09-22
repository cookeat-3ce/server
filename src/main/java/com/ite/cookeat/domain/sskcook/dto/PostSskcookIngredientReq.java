package com.ite.cookeat.domain.sskcook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 슥쿡 재료 등록을 위한 DTO
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
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostSskcookIngredientReq {

  private String name;
  private String amount;
  @Setter
  private Integer sskcookId;
}
