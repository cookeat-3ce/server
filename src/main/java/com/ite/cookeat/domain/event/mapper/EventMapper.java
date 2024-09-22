package com.ite.cookeat.domain.event.mapper;

import com.ite.cookeat.domain.event.dto.GetEventDetailRes;
import com.ite.cookeat.domain.event.dto.GetEventRes;
import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 이벤트와 관련된 요청을 처리하는 Mybatis Mapper
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
public interface EventMapper {

  // filtering 조건으로 이벤트 목록의 페이징 데이터 조회
  List<GetEventRes> selectEventList(@Param("cri") Criteria cri,
      @Param("filtering") String filtering);

  // 이벤트 상세 조회
  GetEventDetailRes selectEventDetail(Integer eventId);

  // 이벤트 전체 조회
  Integer selectEventCount();
}
