package com.ite.cookeat.domain.sskcook.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 비로그인 시 슥쿡 상세 정보를 받기 위한 DTO
 *
 * @author 양재혁
 * @version 1.0
 * @since 2024.08.30
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.30    양재혁       최초 생성
 * </pre>
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GetNullSskcookDetailsReq {

  private Integer sskcookId;

  @Setter
  private List<String> tags;

  @Setter
  private List<GetSskcookIngredientsRes> ingredients;

  @Setter
  private List<GetSskcookDetailsRes> details;
}
