package com.ite.cookeat.domain.sskcook.service;

import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookReq;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookRes;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface SskcookService {

  List<GetFridgeRecipeRes> findMyFridgeRecipe(String username);

  Integer addSskcook(String request, MultipartFile file);

  List<GetSearchSskcookRes> findSearchRecentSskcook(GetSearchSskcookReq getSearchSskcookReq);

  List<GetSearchSskcookRes> findSearchLikesSskcook(GetSearchSskcookReq getSearchSskcookReq);

  List<GetSearchSskcookRes> findRecentSskcook(GetSearchSskcookReq getSearchSskcookReq);

  Integer modifySskcookDeletedate(Integer sskcookId);

  List<GetSearchSskcookRes> findMonthlySskcook(GetSearchSskcookReq getSearchSskcookReq);

  void addLikes(String username, Integer sskcookId);

  void removeLikes(String username, Integer sskcookId);

  Integer findLikes(String username, Integer sskcookId);
}
