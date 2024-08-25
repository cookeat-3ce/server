package com.ite.cookeat.domain.alert.mapper;

import com.ite.cookeat.domain.alert.dto.PostEventAlertMemberReq;

public interface AlertMapper {

  Integer insertEventAlertMember(PostEventAlertMemberReq req);
}
