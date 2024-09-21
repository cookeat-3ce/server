package com.ite.cookeat.domain.live.service;

import com.ite.cookeat.domain.live.dto.GetLiveRes;
import com.ite.cookeat.domain.live.dto.PostLiveReq;
import com.ite.cookeat.global.dto.PaginatedRes;
import org.springframework.web.multipart.MultipartFile;

public interface LiveService {

  Integer saveLive(String dto, MultipartFile file);

  PaginatedRes<GetLiveRes> findLiveList(String keyword, Integer page);

  void modifyLiveEnddate(Integer liveId);

  GetLiveRes findLiveDetail(String sessionId);
}
