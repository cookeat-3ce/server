package com.ite.cookeat.domain.fridge.service;

import com.ite.cookeat.domain.fridge.dto.GetMemberIngredientsRes;
import com.ite.cookeat.domain.fridge.dto.PostMemberIngredientReq;
import java.util.List;

/**
 * 사용자 냉장고의 식재료 조회, 등록, 삭제를 처리하는 Service
 *
 * @author 김지수
 * @version 1.0
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.23    김지수       최초 생성, 식재료 조회
 * 2024.08.24    김지수       식재료 등록, 삭제
 * 2024.09.11    김지수       식재료 조회 시 filtering 조건 추가
 * </pre>
 * @since 2024.08.23
 */
public interface FridgeService {

  // 사용자 냉장고 속 식재료 조회
  List<GetMemberIngredientsRes> findIngredients(String filtering);

  // 식재료 등록
  Integer saveIngredient(PostMemberIngredientReq req);

  // 식재료 삭제
  void modifyIngredientDeletedate(Integer ingredientId);
}
