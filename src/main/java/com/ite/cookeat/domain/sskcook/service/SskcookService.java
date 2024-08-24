package com.ite.cookeat.domain.sskcook.service;

import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookPageRes;
import java.util.List;

public interface SskcookService {

  List<GetFridgeRecipeRes> findMyFridgeRecipe(String username);

  GetSearchSskcookPageRes findSearchRecentSskcook(String keyword, Integer page);

  GetSearchSskcookPageRes findSearchLikesSskcook(String keyword, Integer page);

  GetSearchSskcookPageRes findRecentSskcook(Integer page);

  GetSearchSskcookPageRes findMonthlySskcook(String date, Integer page);
}
