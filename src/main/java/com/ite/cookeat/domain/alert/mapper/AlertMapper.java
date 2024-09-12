package com.ite.cookeat.domain.alert.mapper;

import com.ite.cookeat.domain.alert.dto.PostEventAlertMemberReq;
import java.util.List;

public interface AlertMapper {

  Integer insertEventAlertMember(PostEventAlertMemberReq req);

  List<Integer> selectEventMember(Integer eventId);
}
