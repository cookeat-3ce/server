package com.ite.cookeat.domain.sskcook.service;

import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookReq;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookRes;
import java.util.List;

public interface SskcookService {

  List<GetFridgeRecipeRes> findMyFridgeRecipe(String username);

  List<GetSearchSskcookRes> findSearchRecentSskcook(GetSearchSskcookReq getSearchSskcookReq);

  List<GetSearchSskcookRes> findSearchLikesSskcook(GetSearchSskcookReq getSearchSskcookReq);

  List<GetSearchSskcookRes> findRecentSskcook(GetSearchSskcookReq getSearchSskcookReq);

  List<GetSearchSskcookRes> findMonthlySskcook(GetSearchSskcookReq getSearchSskcookReq);
}
