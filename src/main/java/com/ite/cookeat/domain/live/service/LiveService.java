package com.ite.cookeat.domain.live.service;

import com.ite.cookeat.domain.live.dto.GetLivePageRes;
import com.ite.cookeat.domain.live.dto.PostLiveReq;

public interface LiveService {

  Integer saveLive(PostLiveReq dto);

  GetLivePageRes findLiveList(String keyword, Integer page);
}
