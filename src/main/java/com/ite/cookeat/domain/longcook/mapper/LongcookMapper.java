package com.ite.cookeat.domain.longcook.mapper;

import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import java.util.Optional;

public interface LongcookMapper {

  Optional<GetLongcookRes> selectLongcook(Integer longcookId);
}
