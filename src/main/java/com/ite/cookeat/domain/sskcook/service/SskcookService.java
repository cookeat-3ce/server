package com.ite.cookeat.domain.sskcook.service;

import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookRes;
import com.ite.cookeat.domain.sskcook.dto.GetTotalSskcookDetailsRes;
import com.ite.cookeat.domain.sskcook.dto.PostLikesReq;
import com.ite.cookeat.global.dto.PaginatedRes;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface SskcookService {

  GetTotalSskcookDetailsRes findSskcookTotalDetails(Integer sskcookId);

  List<GetFridgeRecipeRes> findMyFridgeRecipe();

  Integer addSskcook(String request, MultipartFile file);

  PaginatedRes<GetSearchSskcookRes> findSearchRecentSskcookList(String keyword, Integer page);

  PaginatedRes<GetSearchSskcookRes> findSearchLikesSskcookList(String keyword, Integer page);

  PaginatedRes<GetSearchSskcookRes> findRecentSskcookList(Integer page);

  PaginatedRes<GetSearchSskcookRes> findMonthlySskcookList(String date, Integer page);

  PaginatedRes<GetSearchSskcookRes> findUserSskcookList(String username, Integer page);

  PaginatedRes<GetSearchSskcookRes> findTagSskcookList(String tag, Integer page);

  Integer modifySskcookDeletedate(Integer sskcookId);

  void addLikes(PostLikesReq req);

  void removeLikes(PostLikesReq req);

  Integer findLikes(PostLikesReq req);

  void addReport(PostLikesReq postLikesReq);

  void removeReport(PostLikesReq postLikesReq);

  Integer findReport(PostLikesReq postLikesReq);

  Integer modifySskcook(String request, MultipartFile file);
}
