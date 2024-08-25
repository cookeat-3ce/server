package com.ite.cookeat.domain.fridge.mapper;

import com.ite.cookeat.domain.fridge.dto.GetIngredientsRes;
import java.util.List;

public interface FridgeMapper {

  List<GetIngredientsRes> selectIngredients(Integer memberId);
}
