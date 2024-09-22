package com.ite.cookeat.domain.sskcook.mapper;

import com.ite.cookeat.domain.admin.dto.GetTopSskcookRes;
import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import com.ite.cookeat.domain.sskcook.dto.GetNullSskcookDetailsReq;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookRes;
import com.ite.cookeat.domain.sskcook.dto.GetSskcookDetailsReq;
import com.ite.cookeat.domain.sskcook.dto.PostLikesReq;
import com.ite.cookeat.domain.sskcook.dto.PostSskcookReq;
import com.ite.cookeat.domain.sskcook.dto.PutSskcookReq;
import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 슥쿡과 관련된 요청을 처리하는 Mybatis Mapper
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
 * </pre>
 */
public interface SskcookMapper {

  // 최신 순 슥쿡 검색 페이징 조회
  List<GetSearchSskcookRes> selectSearchRecentSskcook(@Param("cri") Criteria cri,
      @Param("keyword") String keyword);

  // 슥쿡 등록
  void addSskcookWithDetails(PostSskcookReq req);

  // 좋아요 순 슥쿡 검색 페이징 조회
  List<GetSearchSskcookRes> selectSearchLikesSskcookList(@Param("cri") Criteria cri,
      @Param("keyword") String keyword);

  // 최신 순 슥쿡 페이징 조회
  List<GetSearchSskcookRes> selectRecentSskcookList(@Param("cri") Criteria cri);

  // 저번 달 좋아요 순 슥쿡 페이징 조회
  List<GetSearchSskcookRes> selectMonthlySskcookList(
      @Param("cri") Criteria cri, @Param("date") String date);

  // 멤버 별 슥쿡 리스트 페이징 조회
  List<GetSearchSskcookRes> selectUserSskcookList(@Param("cri") Criteria cri,
      @Param("username") String username);

  // 태그 별 슥쿡 리스트 페이징 조회
  List<GetSearchSskcookRes> selectTagSskcookList(@Param("cri") Criteria cri,
      @Param("tag") String tag);

  // 슥쿡 검색 리스트 카운트
  Integer selectSearchSskcookListCount(String keyword);

  // 최신 순 슥쿡 리스트 카운트
  Integer selectRecentSskcookListCount();

  // 저번달 좋아요 순 슥쿡 리스트 카운트
  Integer selectMonthlySskcookListCount(String date);

  // 멤버별 슥쿡 리스트 카운트
  Integer selectUserSskcookListCount(String username);

  // 태그별 슥쿡 리스트 카운트
  Integer selectTagSskcookListCount(String tag);

  // 슥쿡 삭제
  void updateSskcookDeletedate(Integer sskcookId);

  // 슥쿡 좋아요 취소
  Integer deleteLikes(PostLikesReq postLikesReq);

  // 슥쿡 좋아요
  Integer insertLikes(PostLikesReq postLikesReq);

  // 슥쿡 좋아요 카운트
  Integer selectLikesCount(PostLikesReq postLikesReq);

  // 슥쿡 신고 취소
  Integer deleteReport(PostLikesReq postLikesReq);

  // 슥쿡 신고
  Integer insertReport(PostLikesReq postLikesReq);

  // 슥쿡 신고 카운트
  Integer selectReportCount(PostLikesReq postLikesReq);

  void selectSskcookDetails(GetSskcookDetailsReq req);

  // 비로그인 시 슥쿡 상세 조회
  void selectNullSskcookDetails(GetNullSskcookDetailsReq req);

  // 슥쿡 변경
  Integer updateSskcookWithDetails(PutSskcookReq putSskcookReq);

  // 슥쿡과 업로드한 사용자 상세 조회
  GetFridgeRecipeRes selectMemberSskcookDetailsBySskcookId(Integer sskcookId);

  List<GetTopSskcookRes> selectTopSskcookList(String yearMonth);
}
