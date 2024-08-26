package com.ite.cookeat.domain.event.mapper;

import com.ite.cookeat.domain.event.dto.GetEventDetailRes;
import com.ite.cookeat.domain.event.dto.GetEventRes;
import com.ite.cookeat.global.dto.Criteria;
import java.util.List;

public interface EventMapper {

  List<GetEventRes> selectEventList(Criteria cri);

  GetEventDetailRes selectEventDetail(Integer eventId);

  Integer selectEventCount();
}
