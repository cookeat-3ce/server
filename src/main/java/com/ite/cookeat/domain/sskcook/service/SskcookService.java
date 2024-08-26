package com.ite.cookeat.domain.sskcook.service;

import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookRes;
import com.ite.cookeat.domain.sskcook.dto.GetTotalSskcookDetailsRes;
import com.ite.cookeat.global.dto.PaginatedRes;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface SskcookService {

  GetTotalSskcookDetailsRes findSskcookTotalDetails(String username, Integer sskcookId)
      throws IOException;

  List<GetFridgeRecipeRes> findMyFridgeRecipe(String username);

  Integer addSskcook(String request, MultipartFile file);

  PaginatedRes<GetSearchSskcookRes> findSearchRecentSskcookList(String keyword, Integer page);

  GetSearchSskcookPageRes findSearchRecentSskcookList(String keyword, Integer page);

  PaginatedRes<GetSearchSskcookRes> findSearchLikesSskcookList(String keyword, Integer page);

  PaginatedRes<GetSearchSskcookRes> findRecentSskcookList(Integer page);

  PaginatedRes<GetSearchSskcookRes> findMonthlySskcookList(String date, Integer page);

  PaginatedRes<GetSearchSskcookRes> findUserSskcookList(String username, Integer page);

  PaginatedRes<GetSearchSskcookRes> findTagSskcookList(String tag, Integer page);

  Integer modifySskcookDeletedate(Integer sskcookId);

  void addLikes(String username, Integer sskcookId);

  void removeLikes(String username, Integer sskcookId);

  Integer findLikes(String username, Integer sskcookId);
}
