package com.ite.cookeat.domain.sskcook.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 태그, 재료를 포함한 통합 슥쿡 상세 정보 DTO
 *
 * @author 양재혁
 * @version 1.0
 * @since 2024.08.26
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.26    양재혁       최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class GetTotalSskcookDetailsRes {

  private List<String> tags;
  private List<GetSskcookIngredientsRes> ingredients;
  private List<GetSskcookDetailsRes> details;
}
