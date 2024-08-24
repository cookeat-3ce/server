package com.ite.cookeat.domain.longcook.service;

import com.ite.cookeat.domain.longcook.dto.GetLongcookReq;
import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;

public interface LongcookService {

  GetLongcookRes findLongcook(Integer longcookId);
  List<GetLongcookRes> findLongcookList(GetLongcookReq getLongcookReq);
}
