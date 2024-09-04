package com.ite.cookeat.domain.live.service;

import com.ite.cookeat.domain.live.dto.GetLiveRes;
import com.ite.cookeat.domain.live.dto.PostLiveReq;
import com.ite.cookeat.global.dto.PaginatedRes;

public interface LiveService {

  Integer saveLive(PostLiveReq dto);

  PaginatedRes<GetLiveRes> findLiveList(String keyword, Integer page);

  void modifyLiveEnddate(Integer liveId);

  GetLiveRes findLiveDetail(String sessionId);
}
