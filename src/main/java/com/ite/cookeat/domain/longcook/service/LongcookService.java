package com.ite.cookeat.domain.longcook.service;

import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import com.ite.cookeat.domain.longcook.dto.GetTotalLongcookDetailsRes;
import com.ite.cookeat.global.dto.PaginatedRes;
import org.springframework.web.multipart.MultipartFile;

public interface LongcookService {

  PaginatedRes<GetLongcookRes> findCreatorLongcookList(String username, Integer page);

  PaginatedRes<GetLongcookRes> findRecentLongcookList(String keyword, Integer page);

  Integer modifyLongcookDeletedate(Integer longcookId);

  Integer modifyLongcook(String request, MultipartFile file);

  Integer addLongcook(String request, MultipartFile file);

  GetTotalLongcookDetailsRes findLongcookTotalDetails(Integer longcookId);
}
