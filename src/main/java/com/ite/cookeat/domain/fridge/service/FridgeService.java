package com.ite.cookeat.domain.fridge.service;

import com.ite.cookeat.domain.fridge.dto.GetIngredientsRes;
import java.util.List;

public interface FridgeService {

  List<GetIngredientsRes> findIngredients(String username);
}
