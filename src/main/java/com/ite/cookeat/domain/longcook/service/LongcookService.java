package com.ite.cookeat.domain.longcook.service;

import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import com.ite.cookeat.domain.longcook.dto.GetTotalLongcookDetailsRes;
import com.ite.cookeat.global.dto.PaginatedRes;
import org.springframework.web.multipart.MultipartFile;

/**
 * 스윽쿡 등록 및 조회, 수정, 삭제 등을 처리하는 Service
 *
 * @author 박유진
 * @version 1.0
 * @since 2024.08.19
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.19    박유진       최초 생성
 * 2024.08.23    양재혁       멤버 별 스윽쿡 리스트 조회
 * 2024.08.24    양재혁       최신 순 스윽쿡 조회
 * 2024.08.25    박유진       스윽쿡 삭제
 * 2024.08.26    박유진       슥윽쿡 수정 및 등록
 * 2024.08.26    박유진       슥윽쿡 검색 및 조회
 * 2024.09.02    박유진       비로그인 상태에서 스윽쿡 조회 가능하도록 수정
 * </pre>
 */
public interface LongcookService {

  PaginatedRes<GetLongcookRes> findCreatorLongcookList(String username, Integer page);

  PaginatedRes<GetLongcookRes> findRecentLongcookList(String keyword, Integer page);

  Integer modifyLongcookDeletedate(Integer longcookId);

  Integer modifyLongcook(String request, MultipartFile file);

  Integer addLongcook(String request, MultipartFile file);

  GetTotalLongcookDetailsRes findLongcookTotalDetails(Integer longcookId);
}
