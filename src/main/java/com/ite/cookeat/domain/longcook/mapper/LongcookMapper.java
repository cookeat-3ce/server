package com.ite.cookeat.domain.longcook.mapper;

import com.ite.cookeat.domain.longcook.dto.GetLongcookDetailsReq;
import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import com.ite.cookeat.domain.longcook.dto.GetNullLongcookDetailsReq;
import com.ite.cookeat.domain.longcook.dto.PostLongcookReq;
import com.ite.cookeat.domain.longcook.dto.PutLongcookReq;
import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LongcookMapper {

  List<GetLongcookRes> selectLongcookList(@Param("cri") Criteria cri,
      @Param("username") String username);

  Integer selectLongcookListCount(String username);

  List<GetLongcookRes> selectRecentLongcookList(@Param("cri") Criteria cri,
      @Param("keyword") String keyword);

  Integer selectRecentLongcookListCount(@Param("keyword") String keyword);

  Integer updateLongcookDeletedate(Integer longcookId);

  void addLongcookWithDetails(PostLongcookReq req);

  Integer updateLongcookWithDetails(PutLongcookReq putLongcookReq);

  void selectNullLongcookDetails(GetNullLongcookDetailsReq req);

  void selectLongcookDetails(GetLongcookDetailsReq req);
}
