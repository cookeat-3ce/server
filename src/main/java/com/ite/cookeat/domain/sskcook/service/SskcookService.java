package com.ite.cookeat.domain.sskcook.service;

import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface SskcookService {

  List<GetFridgeRecipeRes> findMyFridgeRecipe(String username);

  Integer addSskcook(String postSskcookReq, MultipartFile file);
}