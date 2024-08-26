package com.ite.cookeat.domain.live.mapper;

import com.ite.cookeat.domain.live.dto.GetLiveRes;
import com.ite.cookeat.domain.live.dto.PostLiveReq;
import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LiveMapper {

  void insertLive(PostLiveReq req);

  int selectLiveCount(@Param("keyword") String keyword);

  List<GetLiveRes> selectLiveListByKeyword(
      @Param("cri") Criteria cri,
      @Param("keyword") String keyword);
}
