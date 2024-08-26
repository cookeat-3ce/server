package com.ite.cookeat.domain.longcook.service;

import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import com.ite.cookeat.global.dto.PaginatedRes;

public interface LongcookService {

  GetLongcookRes findLongcook(Integer longcookId);

  PaginatedRes<GetLongcookRes> findLongcookList(String username, Integer page);

  PaginatedRes<GetLongcookRes> findRecentLongcookList(Integer page);

  Integer modifyLongcookDeletedate(Integer longcookId);
}
