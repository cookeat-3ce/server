package com.ite.cookeat.domain.fridge.mapper;

import com.ite.cookeat.domain.fridge.dto.GetMemberIngredientsRes;
import com.ite.cookeat.domain.fridge.dto.PostMemberIngredientReq;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 사용자 냉장고와 관련된 요청을 처리하는 Mybatis Mapper
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
public interface FridgeMapper {

  // filtering 조건으로 식재료 목록의 페이징 데이터 조회
  List<GetMemberIngredientsRes> selectIngredients(@Param("memberId") Integer memberId,
      @Param("filtering") String filtering);

  // 식재료 추가
  void insertIngredient(PostMemberIngredientReq req);

  // 식재료 삭제
  Integer updateIngredientDeletedate(Integer ingredientId);
}
