package com.ite.cookeat.domain.fridge.mapper;

import com.ite.cookeat.domain.fridge.dto.GetMemberIngredientsRes;
import com.ite.cookeat.domain.fridge.dto.PostMemberIngredientReq;
import java.util.List;

public interface FridgeMapper {

  List<GetMemberIngredientsRes> selectIngredients(Integer memberId);

  void insertIngredient(PostMemberIngredientReq req);

  Integer updateIngredientDeletedate(Integer ingredientId);
}
