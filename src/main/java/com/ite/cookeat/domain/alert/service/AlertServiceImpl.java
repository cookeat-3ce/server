package com.ite.cookeat.domain.alert.service;

import static com.ite.cookeat.exception.ErrorCode.ADD_MEMBER_TO_ALERT_CONFLICT;

import com.ite.cookeat.domain.alert.dto.PostEventAlertMemberReq;
import com.ite.cookeat.domain.alert.mapper.AlertMapper;
import com.ite.cookeat.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlertServiceImpl implements AlertService {

  private final AlertMapper alertMapper;

  @Override
  @Transactional
  public Integer addEventAlertMember(PostEventAlertMemberReq req) {
    alertMapper.insertEventAlertMember(req);
    if (req.getResult() <= 0) {
      throw new CustomException(ADD_MEMBER_TO_ALERT_CONFLICT);
    }
    return req.getResult();
  }
}
