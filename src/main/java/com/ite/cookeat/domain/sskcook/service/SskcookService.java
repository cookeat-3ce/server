package com.ite.cookeat.domain.sskcook.service;

import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookPageRes;
import java.util.List;

public interface SskcookService {

  List<GetFridgeRecipeRes> findMyFridgeRecipe(String username);

  GetSearchSskcookPageRes findSearchRecentSskcookList(String keyword, Integer page);

  GetSearchSskcookPageRes findSearchLikesSskcookList(String keyword, Integer page);

  GetSearchSskcookPageRes findRecentSskcookList(Integer page);

  GetSearchSskcookPageRes findMonthlySskcookList(String date, Integer page);

  GetSearchSskcookPageRes findUserSskcookList(String username, Integer page);

  GetSearchSskcookPageRes findTagSskcookList(String tag, Integer page);
}
