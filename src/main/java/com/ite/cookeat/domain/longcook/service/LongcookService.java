package com.ite.cookeat.domain.longcook.service;

import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import java.util.List;
import com.ite.cookeat.global.dto.PaginatedRes;
import org.springframework.web.multipart.MultipartFile;

public interface LongcookService {

  GetLongcookRes findLongcook(Integer longcookId);

  PaginatedRes<GetLongcookRes> findLongcookList(String username, Integer page);

  PaginatedRes<GetLongcookRes> findRecentLongcookList(Integer page);

  Integer modifyLongcookDeletedate(Integer longcookId);

  Integer modifyLongcook(String request, MultipartFile file);

  Integer addLongcook(String request, MultipartFile file);

}
