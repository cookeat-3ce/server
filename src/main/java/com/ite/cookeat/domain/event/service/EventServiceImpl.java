package com.ite.cookeat.domain.event.service;

import com.ite.cookeat.domain.event.dto.GetEventDetailRes;
import com.ite.cookeat.domain.event.dto.GetEventRes;
import com.ite.cookeat.domain.event.mapper.EventMapper;
import com.ite.cookeat.global.dto.Criteria;
import com.ite.cookeat.global.dto.PaginatedRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

  private final EventMapper eventMapper;

  @Override
  @Transactional(readOnly = true)
  public PaginatedRes<GetEventRes> findEventList(Integer page, String filtering) {
    Criteria cri = Criteria.builder()
        .pageSize(filtering.equals("recipe") ? 3 : 6)
        .pageNum(page)
        .build();

    return PaginatedRes.<GetEventRes>builder()
        .total(eventMapper.selectEventCount())
        .data(eventMapper.selectEventList(cri, filtering))
        .cri(cri)
        .build();
  }

  @Override
  @Transactional(readOnly = true)
  public GetEventDetailRes findEventDetail(Integer eventId) {
    return eventMapper.selectEventDetail(eventId);
  }
}
