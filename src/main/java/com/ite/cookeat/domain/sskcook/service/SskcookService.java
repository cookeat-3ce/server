package com.ite.cookeat.domain.sskcook.service;

import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookPageRes;
import com.ite.cookeat.domain.sskcook.dto.GetTotalSskcookDetailsRes;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface SskcookService {

  GetTotalSskcookDetailsRes findSskcookTotalDetails(String username, Integer sskcookId)
      throws IOException;

  List<GetFridgeRecipeRes> findMyFridgeRecipe(String username);

  Integer addSskcook(String request, MultipartFile file);

  GetSearchSskcookPageRes findSearchRecentSskcookList(String keyword, Integer page);

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
