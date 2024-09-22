package com.ite.cookeat.domain.live.service;

import com.ite.cookeat.domain.live.dto.GetLiveRes;
import com.ite.cookeat.global.dto.PaginatedRes;
import org.springframework.web.multipart.MultipartFile;

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
public interface LiveService {

  // 실시간 요리 클래스(라이브) 등록
  Integer saveLive(String dto, MultipartFile file);

  PaginatedRes<GetLiveRes> findLiveList(String keyword, Integer page);

  // 라이브 종료
  void modifyLiveEnddate(Integer liveId);

  // 라이브 상세 조회
  GetLiveRes findLiveDetail(String sessionId);
}
