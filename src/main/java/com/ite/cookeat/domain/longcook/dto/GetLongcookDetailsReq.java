package com.ite.cookeat.domain.longcook.dto;

import com.ite.cookeat.domain.sskcook.dto.GetSskcookDetailsRes;
import com.ite.cookeat.domain.sskcook.dto.GetSskcookIngredientsRes;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 스윽쿡 상세 조회를 위한 DTO
 *
 * @author 박유진
 * @version 1.0
 * @since 2024.09.02
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.09.02    박유진        최초 생성, 비로그인 상태에서 롱쿡 조회 가능하도록 수정
 * </pre>
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GetLongcookDetailsReq {
  private String username;
  private Integer longcookId;

  @Setter
  private List<GetLongcookIngredientsRes> ingredients;

  @Setter
  private List<GetLongcookDetailsRes> details;
}
