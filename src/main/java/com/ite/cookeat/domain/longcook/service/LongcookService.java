package com.ite.cookeat.domain.longcook.service;

import com.ite.cookeat.domain.longcook.dto.GetLongcookDetailRes;
import com.ite.cookeat.global.dto.PaginatedRes;
import org.springframework.web.multipart.MultipartFile;

public interface LongcookService {

  GetLongcookDetailRes findLongcook(Integer longcookId);

  PaginatedRes<GetLongcookDetailRes> findCreatorLongcookList(String username, Integer page);

  PaginatedRes<GetLongcookDetailRes> findRecentLongcookList(String keyword, Integer page);

  Integer modifyLongcookDeletedate(Integer longcookId);

  Integer modifyLongcook(String request, MultipartFile file);

  Integer addLongcook(String request, MultipartFile file);

}
