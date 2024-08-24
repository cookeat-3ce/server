package com.ite.cookeat.domain.longcook.mapper;

import com.ite.cookeat.domain.longcook.dto.GetLongcookReq;
import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import java.util.List;

public interface LongcookMapper {

  List<GetLongcookRes> selectLongcook(GetLongcookReq getLongcookReq);
}
