package com.ite.cookeat.domain.sskcook.service;

import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import com.ite.cookeat.domain.sskcook.dto.PostSskcookReq;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface SskcookService {

  List<GetFridgeRecipeRes> findMyFridgeRecipe(String username);

  Integer addSskcook(PostSskcookReq postSskcookReq, MultipartFile file);
}