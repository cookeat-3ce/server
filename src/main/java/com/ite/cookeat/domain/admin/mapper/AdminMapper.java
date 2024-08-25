package com.ite.cookeat.domain.admin.mapper;

import com.ite.cookeat.domain.admin.dto.GetVerifyRequestRes;
import com.ite.cookeat.domain.admin.dto.PostEventReq;
import com.ite.cookeat.domain.admin.dto.PostVerifyRequestReq;
import com.ite.cookeat.global.dto.Criteria;
import java.util.List;

public interface AdminMapper {

  List<GetVerifyRequestRes> selectVerifyRequestList(Criteria cri);

  Integer selectVerifyRequestCount();

  Integer updateVerifyRequestMemberStatus(PostVerifyRequestReq req);

  Integer insertEvent(PostEventReq req);
}
