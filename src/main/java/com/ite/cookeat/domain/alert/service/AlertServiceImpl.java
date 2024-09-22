package com.ite.cookeat.domain.alert.service;

import static com.ite.cookeat.exception.ErrorCode.ADD_MEMBER_TO_ALERT_CONFLICT;

import com.ite.cookeat.domain.alert.dto.PostEventAlertMemberReq;
import com.ite.cookeat.domain.alert.dto.PostEventAlertReq;
import com.ite.cookeat.domain.alert.mapper.AlertMapper;
import com.ite.cookeat.domain.member.service.MemberService;
import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.util.SecurityUtils;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
 * 2024.09.09    김지수       이벤트 사전알림 신청 Stored Procedure 적용
 * 2024.09.11    김지수       SSE 발생
 * </pre>
 * @since 2024.08.26
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AlertServiceImpl implements AlertService {

  private static final long TIMEOUT = 24 * 60 * 60 * 1000;
  private static final long RECONNECTION_TIMEOUT = 1000L;
  private final AlertMapper alertMapper;
  private final MemberService memberService;
  private final Map<Integer, SseEmitter> emitters = new ConcurrentHashMap<>();

  /**
   * 이벤트의 알림 목록에 사용자 등록
   *
   * @param req (이벤트와 알림 신청한 사용자 정보)
   * @return 1 이상이면 이벤트 사전 알림 등록 요청 성공
   */
  @Override
  @Transactional
  public Integer addEventAlertMember(PostEventAlertMemberReq req) {
    alertMapper.insertEventAlertMember(req);
    if (req.getResult() <= 0) {
      throw new CustomException(ADD_MEMBER_TO_ALERT_CONFLICT);
    }
    return req.getResult();
  }

  /**
   * 이벤트에 등록한 사용자들에 SSE 발생
   *
   * @param req (이벤트 등록한 사용자들에 SSE 발생)
   */
  @Override
  @Transactional
  public void sendAlert(PostEventAlertReq req) {
    for (Integer id : alertMapper.selectEventMember(req.getEventId())) {
      sendNotificationToMember(id, req.getMessage());
    }
  }

  /**
   * 이벤트의 SSE 목록에 사용자 추가 (이벤트 구독)
   *
   * @param memberId (등록할 사용자 ID)
   * @param message  (발송할 message)
   */
  private void sendNotificationToMember(Integer memberId, String message) {
    SseEmitter emitter = emitters.get(memberId);
    if (emitter != null) {
      try {
        emitter.send(
            SseEmitter.event().name("newNotice").data(message).reconnectTime(RECONNECTION_TIMEOUT));
      } catch (IOException e) {
        emitters.remove(memberId);
        emitter.completeWithError(e);
      }
    }
  }

  /**
   * 사용자 SSE 연결
   *
   * @return SSE 객체
   */
  @Override
  @Transactional
  public SseEmitter createSseEmitter() {
    SseEmitter emitter = new SseEmitter(TIMEOUT);
    Integer memberId = memberService.findMemberId(SecurityUtils.getCurrentUsername());
    emitters.put(memberId, emitter);

    emitter.onCompletion(() -> {
      log.info("on completion");
      emitters.remove(memberId);
    });
    emitter.onTimeout(() -> {
      log.info("on timeout");
      emitters.remove(memberId);
    });
    emitter.onError(e -> {
      log.error("on error: {}", e.getMessage());
      emitters.remove(memberId);
    });

    try {
      log.info("sse send: {}", memberId);
      emitter.send(SseEmitter.event().name("init").data("SSE connection established")
          .reconnectTime(RECONNECTION_TIMEOUT));
    } catch (IOException e) {
      emitter.completeWithError(e);
    }

    return emitter;
  }
}
