package com.ite.cookeat.domain.event.service;

import com.ite.cookeat.domain.event.dto.GetEventDetailRes;
import com.ite.cookeat.domain.event.dto.GetEventRes;
import com.ite.cookeat.global.dto.PaginatedRes;

public interface EventService {

  PaginatedRes<GetEventRes> findEventList(Integer page, String filtering);

  GetEventDetailRes findEventDetail(Integer eventId);
}
