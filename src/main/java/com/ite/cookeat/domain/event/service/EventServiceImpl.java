package com.ite.cookeat.domain.event.service;

import com.ite.cookeat.domain.event.dto.GetEventDetailRes;
import com.ite.cookeat.domain.event.dto.GetEventPageRes;
import com.ite.cookeat.domain.event.mapper.EventMapper;
import com.ite.cookeat.global.dto.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

  private final EventMapper eventMapper;

  @Override
  public GetEventPageRes findEventList(Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .build();
    return GetEventPageRes.builder()
        .cri(cri)
        .total(eventMapper.selectEventCount())
        .events(eventMapper.selectEventList(cri))
        .build();
  }

  @Override
  public GetEventDetailRes findEventDetail(Integer eventId) {
    return eventMapper.selectEventDetail(eventId);
  }
}
