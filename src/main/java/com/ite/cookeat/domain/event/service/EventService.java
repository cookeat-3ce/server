package com.ite.cookeat.domain.event.service;

import com.ite.cookeat.domain.event.dto.GetEventDetailRes;
import com.ite.cookeat.domain.event.dto.GetEventPageRes;

public interface EventService {

  GetEventPageRes findEventList(Integer page);

  GetEventDetailRes findEventDetail(Integer eventId);
}
