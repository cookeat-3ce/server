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

@Service
@RequiredArgsConstructor
@Slf4j
public class AlertServiceImpl implements AlertService {

  private static final long TIMEOUT = 24 * 60 * 60 * 1000;
  private static final long RECONNECTION_TIMEOUT = 1000L;
  private final AlertMapper alertMapper;
  private final MemberService memberService;
  private final Map<Integer, SseEmitter> emitters = new ConcurrentHashMap<>();

  @Override
  @Transactional
  public Integer addEventAlertMember(PostEventAlertMemberReq req) {
    alertMapper.insertEventAlertMember(req);
    if (req.getResult() <= 0) {
      throw new CustomException(ADD_MEMBER_TO_ALERT_CONFLICT);
    }
    return req.getResult();
  }

  @Override
  @Transactional
  public void sendAlert(PostEventAlertReq req) {
    for (Integer id : alertMapper.selectEventMember(req.getEventId())) {
      sendNotificationToMember(id, req.getMessage());
    }
  }

  private void sendNotificationToMember(Integer memberId, String message) {
    SseEmitter emitter = emitters.get(memberId);
    if (emitter != null) {
      try {
        log.info("send new notice to member {}", memberId);
        emitter.send(
            SseEmitter.event().name("newNotice").data(message).reconnectTime(RECONNECTION_TIMEOUT));
      } catch (IOException e) {
        emitters.remove(memberId);
        emitter.completeWithError(e);
      }
    }
  }

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
