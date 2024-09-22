package com.ite.cookeat.domain.live.mapper;

import com.ite.cookeat.domain.live.dto.GetLiveRes;
import com.ite.cookeat.domain.live.dto.PostLiveReq;
import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;

/**
 * 실시간 요리 클래스와 관련된 요청을 처리하는 Mybatis Mapper
 *
 * @author 김지수
 * @version 1.0
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.19    박유진       최초 생성
 * 2024.08.23    김지수       라이브 등록
 * 2024.08.25    박유진       진행 중인 라이브 목록 조회
 * 2024.09.04    김지수       라이브 종료, 상세 조회
 * </pre>
 * @since 2024.08.19
 */
public interface LiveMapper {

  // 라이브 등록
  void insertLive(PostLiveReq req);

  // 진행 중인 전체 라이브 수
  int selectLiveCount(@Param("keyword") String keyword);

  // keyword로 라이브 목록의 페이징 데이터 조회
  List<GetLiveRes> selectLiveListByKeyword(
      @Param("cri") Criteria cri,
      @Param("keyword") String keyword);

  // 라이브 종료
  Integer updateLiveEnddate(Integer liveId);

  // 라이브 상세 조회
  Optional<GetLiveRes> selectLiveDetail(String sessionId);
}
