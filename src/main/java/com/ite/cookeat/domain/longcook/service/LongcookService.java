package com.ite.cookeat.domain.longcook.service;

import com.ite.cookeat.domain.longcook.dto.GetLongcookReq;
import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import java.util.List;

public interface LongcookService {

  List<GetLongcookRes> findLongcook(GetLongcookReq getLongcookReq);

}
