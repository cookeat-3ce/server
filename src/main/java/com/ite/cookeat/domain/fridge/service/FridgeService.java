package com.ite.cookeat.domain.fridge.service;

import com.ite.cookeat.domain.fridge.dto.GetIngredientsRes;
import com.ite.cookeat.domain.fridge.dto.PostIngredientReq;
import java.util.List;

public interface FridgeService {

  List<GetIngredientsRes> findIngredients(String username);

  Integer saveIngredient(PostIngredientReq req);

  void modifyIngredientDeletedate(Integer ingredientId);
}
