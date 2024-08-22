package com.ite.cookeat.domain.sskcook.service;

import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import com.ite.cookeat.domain.sskcook.dto.GetSearchRecentSskcookReq;
import com.ite.cookeat.domain.sskcook.dto.GetSearchRecentSskcookRes;
import java.util.List;

public interface SskcookService {

  List<GetFridgeRecipeRes> findMyFridgeRecipe(String username);
  List<GetSearchRecentSskcookRes> findSearchRecentSskcook(GetSearchRecentSskcookReq getSearchRecentSskcookReq);
}
