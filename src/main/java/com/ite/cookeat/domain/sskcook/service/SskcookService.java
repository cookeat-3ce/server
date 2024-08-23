package com.ite.cookeat.domain.sskcook.service;

import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookRes;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookReq;
import java.util.List;

public interface SskcookService {

  List<GetFridgeRecipeRes> findMyFridgeRecipe(String username);

  List<GetSearchSskcookRes> findSearchRecentSskcook(GetSearchSskcookReq getSearchSskcookReq);
  List<GetSearchSskcookRes> findSearchLikesSskcook(GetSearchSskcookReq getSearchSskcookReq);
}
