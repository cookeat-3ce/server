package com.ite.cookeat.domain.fridge.mapper;

import com.ite.cookeat.domain.fridge.dto.GetIngredientsRes;
import com.ite.cookeat.domain.fridge.dto.PostIngredientReq;
import java.util.List;

public interface FridgeMapper {

  List<GetIngredientsRes> selectIngredients(Integer memberId);

  void insertIngredient(PostIngredientReq req);
}
