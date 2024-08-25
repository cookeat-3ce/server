package com.ite.cookeat.domain.fridge.service;

import com.ite.cookeat.domain.fridge.dto.GetMemberIngredientsRes;
import com.ite.cookeat.domain.fridge.dto.PostMemberIngredientReq;
import java.util.List;

public interface FridgeService {

  List<GetMemberIngredientsRes> findIngredients(String username);

  Integer saveIngredient(PostMemberIngredientReq req);

  void modifyIngredientDeletedate(Integer ingredientId);
}
