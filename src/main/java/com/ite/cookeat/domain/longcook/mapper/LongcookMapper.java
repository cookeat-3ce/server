package com.ite.cookeat.domain.longcook.mapper;

import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LongcookMapper {

  List<GetLongcookRes> selectLongcookList(@Param("cri") Criteria cri,
      @Param("username") String username);

  Integer selectLongcookListCount(String username);
}
