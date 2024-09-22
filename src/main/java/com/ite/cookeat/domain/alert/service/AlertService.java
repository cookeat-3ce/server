package com.ite.cookeat.domain.alert.service;

import com.ite.cookeat.domain.alert.dto.PostEventAlertMemberReq;
import com.ite.cookeat.domain.alert.dto.PostEventAlertReq;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * 사용자 SSE 목록에 등록, SSE 발생 등을 처리하는 Service
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
public interface AlertService {

  // 이벤트의 알림 목록에 사용자 등록
  Integer addEventAlertMember(PostEventAlertMemberReq req);

  // 이벤트에 등록한 사용자들에 SSE 발생
  void sendAlert(PostEventAlertReq req);

  // 이벤트의 SSE 목록에 사용자 추가 (이벤트 구독)
  SseEmitter createSseEmitter();
}
