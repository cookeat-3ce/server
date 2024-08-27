package com.ite.cookeat.domain.admin.mapper;

import com.ite.cookeat.domain.admin.dto.GetReportSskcookRes;
import com.ite.cookeat.domain.admin.dto.GetVerifyRequestRes;
import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {

  List<GetVerifyRequestRes> selectVerifyRequestList(Criteria cri);

  Integer selectVerifyRequestCount();

  Integer updateVerifyRequestMemberStatus(@Param("username") String username,
      @Param("verifiedStatus") String verifiedStatus);

  List<GetReportSskcookRes> selectReportSskcookList(Criteria cri);

  Integer selectReportSskcookCount();
}
