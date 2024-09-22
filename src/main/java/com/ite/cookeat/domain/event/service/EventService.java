package com.ite.cookeat.domain.event.service;

import com.ite.cookeat.domain.event.dto.GetEventDetailRes;
import com.ite.cookeat.domain.event.dto.GetEventRes;
import com.ite.cookeat.global.dto.PaginatedRes;

/**
 * 이벤트 목록, 상세 조회를 처리하는 Service
 *
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
public interface EventService {

  // 이벤트 목록 조회
  PaginatedRes<GetEventRes> findEventList(Integer page, String filtering);

  // 이벤트 상세 조회
  GetEventDetailRes findEventDetail(Integer eventId);
}
