package com.ite.cookeat.domain.longcook.mapper;

import com.ite.cookeat.domain.longcook.dto.GetLongcookDetailsReq;
import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import com.ite.cookeat.domain.longcook.dto.GetNullLongcookDetailsReq;
import com.ite.cookeat.domain.longcook.dto.PostLongcookReq;
import com.ite.cookeat.domain.longcook.dto.PutLongcookReq;
import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 스윽쿡과 관련된 요청을 처리하는 Mybatis Mapper
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
 * 2024.08.25    박유진       스윽쿡 삭제
 * 2024.08.26    박유진       슥윽쿡 수정 및 등록
 * 2024.08.26    박유진       슥윽쿡 검색 및 조회
 * 2024.09.02    박유진       비로그인 상태에서 스윽쿡 조회 가능하도록 수정
 * </pre>
 */
@Mapper
public interface LongcookMapper {

  // 스윽쿡 리스트 조회
  List<GetLongcookRes> selectLongcookList(@Param("cri") Criteria cri,
      @Param("username") String username);

  Integer selectLongcookListCount(String username);

  // 스윽쿡 검색 조회
  List<GetLongcookRes> selectRecentLongcookList(@Param("cri") Criteria cri,
      @Param("keyword") String keyword);

  // 업로드 된 스윽쿡 리스트 갯수 조회
  Integer selectRecentLongcookListCount(@Param("keyword") String keyword);

  // 스윽쿡 삭제
  Integer updateLongcookDeletedate(Integer longcookId);

  // 스윽쿡 등록
  void addLongcookWithDetails(PostLongcookReq req);

  // 스윽쿡 수정
  Integer updateLongcookWithDetails(PutLongcookReq putLongcookReq);

  // 비로그인 상태에서 스윽쿡 상세조회
  void selectNullLongcookDetails(GetNullLongcookDetailsReq req);

  // 스윽쿡 상세조회
  void selectLongcookDetails(GetLongcookDetailsReq req);
}
