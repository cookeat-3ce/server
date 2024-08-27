package com.ite.cookeat.domain.event.service;

import com.ite.cookeat.domain.event.dto.GetEventDetailRes;
import com.ite.cookeat.domain.event.dto.GetEventRes;
import com.ite.cookeat.domain.event.mapper.EventMapper;
import com.ite.cookeat.global.dto.Criteria;
import com.ite.cookeat.global.dto.PaginatedRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

  private final EventMapper eventMapper;

  @Override
  public PaginatedRes<GetEventRes> findEventList(Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .build();

    return PaginatedRes.<GetEventRes>builder()
        .total(eventMapper.selectEventCount())
        .data(eventMapper.selectEventList(cri))
        .cri(cri)
        .build();
  }

  @Override
  public GetEventDetailRes findEventDetail(Integer eventId) {
    return eventMapper.selectEventDetail(eventId);
  }
}
