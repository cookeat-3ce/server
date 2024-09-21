package com.ite.cookeat.domain.event.mapper;

import com.ite.cookeat.domain.event.dto.GetEventDetailRes;
import com.ite.cookeat.domain.event.dto.GetEventRes;
import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EventMapper {

  List<GetEventRes> selectEventList(@Param("cri") Criteria cri,
      @Param("filtering") String filtering);

  GetEventDetailRes selectEventDetail(Integer eventId);

  Integer selectEventCount();
}
