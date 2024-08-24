package com.ite.cookeat.domain.longcook.service;

import com.ite.cookeat.domain.longcook.dto.GetLongCookPageRes;

public interface LongcookService {

  GetLongCookPageRes findLongcookList(String username, Integer page);

}
