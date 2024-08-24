package com.ite.cookeat.domain.longcook.service;

import com.ite.cookeat.domain.longcook.dto.GetLongCookPageRes;
import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;

public interface LongcookService {

  GetLongcookRes findLongcook(Integer longcookId);

  GetLongCookPageRes findLongcookList(String username, Integer page);
}
