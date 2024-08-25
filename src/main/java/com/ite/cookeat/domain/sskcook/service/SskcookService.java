package com.ite.cookeat.domain.sskcook.service;

import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookPageRes;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface SskcookService {

  List<GetFridgeRecipeRes> findMyFridgeRecipe(String username);

  GetSearchSskcookPageRes findSearchRecentSskcookList(String keyword, Integer page);

  Integer addSskcook(String postSskcookReq, MultipartFile file);


  GetSearchSskcookPageRes findSearchLikesSskcookList(String keyword, Integer page);

  GetSearchSskcookPageRes findRecentSskcookList(Integer page);

  GetSearchSskcookPageRes findMonthlySskcookList(String date, Integer page);

  GetSearchSskcookPageRes findUserSskcookList(String username, Integer page);

  GetSearchSskcookPageRes findTagSskcookList(String tag, Integer page);

  Integer modifySskcookDeletedate(Integer sskcookId);


  void addLikes(String username, Integer sskcookId);

  void removeLikes(String username, Integer sskcookId);

  Integer findLikes(String username, Integer sskcookId);
}
