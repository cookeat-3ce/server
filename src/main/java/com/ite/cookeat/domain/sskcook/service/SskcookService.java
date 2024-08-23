package com.ite.cookeat.domain.sskcook.service;

import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import java.util.List;

public interface SskcookService {

  List<GetFridgeRecipeRes> findMyFridgeRecipe(String username);
}