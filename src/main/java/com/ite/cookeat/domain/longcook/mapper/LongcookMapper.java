package com.ite.cookeat.domain.longcook.mapper;

import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LongcookMapper {

  Optional<GetLongcookRes> selectLongcook(Integer longcookId);

  List<GetLongcookRes> selectLongcookList(@Param("cri") Criteria cri,
      @Param("username") String username);

  Integer selectLongcookListCount(String username);

  List<GetLongcookRes> selectRecentLongcookList(@Param("cri") Criteria cri);

  Integer selectRecentLongcookListCount();
}
