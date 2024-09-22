package com.ite.cookeat.domain.event.service;

import com.ite.cookeat.domain.event.dto.GetEventDetailRes;
import com.ite.cookeat.domain.event.dto.GetEventRes;
import com.ite.cookeat.domain.event.mapper.EventMapper;
import com.ite.cookeat.global.dto.Criteria;
import com.ite.cookeat.global.dto.PaginatedRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 김지수
 * @version 1.0
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.25    김지수       최초 생성, 이벤트 목록 및 상세 조회
 * 2024.09.21    김지수       이벤트 목록 조회 시 filtering 조건 추가
 * </pre>
 * @since 2024.08.25
 */
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

  private final EventMapper eventMapper;

  /**
   * 이벤트 목록 조회 (페이징, filtering 조건)
   *
   * @param page      (페이지 번호)
   * @param filtering (조건부 검색)
   * @return 필터링된 이벤트 목록 데이터의 페이징 조회
   */
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

  /**
   * 이벤트 상세 조회
   *
   * @param eventId (이벤트 ID)
   * @return 이벤트 상세 데이터
   */
  @Override
  @Transactional(readOnly = true)
  public GetEventDetailRes findEventDetail(Integer eventId) {
    return eventMapper.selectEventDetail(eventId);
  }
}
