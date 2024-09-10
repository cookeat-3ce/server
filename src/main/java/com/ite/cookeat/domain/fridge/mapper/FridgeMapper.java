package com.ite.cookeat.domain.fridge.mapper;

import com.ite.cookeat.domain.fridge.dto.GetMemberIngredientsRes;
import com.ite.cookeat.domain.fridge.dto.PostMemberIngredientReq;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FridgeMapper {

  List<GetMemberIngredientsRes> selectIngredients(@Param("memberId") Integer memberId,
      @Param("filtering") String filtering);

  void insertIngredient(PostMemberIngredientReq req);

  Integer updateIngredientDeletedate(Integer ingredientId);
}
