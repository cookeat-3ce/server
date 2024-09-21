package com.ite.cookeat.domain.admin.mapper;

import com.ite.cookeat.domain.admin.dto.DeleteReportSskcookReq;
import com.ite.cookeat.domain.admin.dto.GetReportSskcookRes;
import com.ite.cookeat.domain.admin.dto.GetVerifyRequestRes;
import com.ite.cookeat.domain.admin.dto.PostEventReq;
import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 관리자와 관련된 요청을 처리하는 Mybatis Mapper
 *
 * @author 김지수
 * @version 1.0
 * @since 2024.08.25
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.25    김지수       최초 생성
 * </pre>
 */
public interface AdminMapper {

  // 사용자 인증 요청 페이징 조회
  List<GetVerifyRequestRes> selectVerifyRequestList(Criteria cri);

  // 현재 들어온 사용자 인증 요청의 갯수 조회
  Integer selectVerifyRequestCount();

  // 이벤트 등록
  Integer insertEvent(PostEventReq req);

  // 사용자 인증 요청에 대한 상태 변경 (기존 -> verifiedStatus)
  Integer updateVerifyRequestMemberStatus(@Param("username") String username,
      @Param("verifiedStatus") String verifiedStatus);

  // 슥쿡 신고 목록 조회
  List<GetReportSskcookRes> selectReportSskcookList(Criteria cri);

  // 신고된 슥쿡의 갯수 조회
  Integer selectReportSskcookCount();

  // 신고된 슥쿡의 상태 변경 (기존 -> BLOCKED)
  Integer updateReportSskcookStatus(DeleteReportSskcookReq req);
}
