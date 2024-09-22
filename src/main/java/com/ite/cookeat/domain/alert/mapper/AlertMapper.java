package com.ite.cookeat.domain.alert.mapper;

import com.ite.cookeat.domain.alert.dto.PostEventAlertMemberReq;
import java.util.List;

/**
 * 알림과 관련된 요청을 처리하는 Mybatis Mapper
 *
 * @author 김지수
 * @version 1.0
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.26    김지수       최초 생성, 사용자 SSE 등록
 * 2024.09.11    김지수       SSE 발생
 * </pre>
 * @since 2024.08.26
 */
public interface AlertMapper {

  // 특정 이벤트에 알림 신청한 사용자 데이터 등록
  Integer insertEventAlertMember(PostEventAlertMemberReq req);

  // 특정 이벤트에 알림 받기 신청한 사용자 목록 조회
  List<Integer> selectEventMember(Integer eventId);
}
