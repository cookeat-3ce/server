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
 * @since 2024.08.23
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.19    박유진       최초 생성
 * </pre>
 */
public interface SskcookMapper {


  List<GetSearchSskcookRes> selectSearchRecentSskcook(@Param("cri") Criteria cri,
      @Param("keyword") String keyword);

  // 슥쿡 등록
  void addSskcookWithDetails(PostSskcookReq req);

  List<GetSearchSskcookRes> selectSearchLikesSskcookList(@Param("cri") Criteria cri,
      @Param("keyword") String keyword);

  List<GetSearchSskcookRes> selectRecentSskcookList(@Param("cri") Criteria cri);

  List<GetSearchSskcookRes> selectMonthlySskcookList(
      @Param("cri") Criteria cri, @Param("date") String date);

  List<GetSearchSskcookRes> selectUserSskcookList(@Param("cri") Criteria cri,
      @Param("username") String username);

  List<GetSearchSskcookRes> selectTagSskcookList(@Param("cri") Criteria cri,
      @Param("tag") String tag);

  Integer selectSearchSskcookListCount(String keyword);

  Integer selectRecentSskcookListCount();

  Integer selectMonthlySskcookListCount(String date);

  Integer selectUserSskcookListCount(String username);

  Integer selectTagSskcookListCount(String tag);

  void updateSskcookDeletedate(Integer sskcookId);

  Integer deleteLikes(PostLikesReq postLikesReq);

  Integer insertLikes(PostLikesReq postLikesReq);

  Integer selectLikesCount(PostLikesReq postLikesReq);

  Integer deleteReport(PostLikesReq postLikesReq);

  Integer insertReport(PostLikesReq postLikesReq);

  Integer selectReportCount(PostLikesReq postLikesReq);

  void selectSskcookDetails(GetSskcookDetailsReq req);

  void selectNullSskcookDetails(GetNullSskcookDetailsReq req);

  // 슥쿡 변경
  Integer updateSskcookWithDetails(PutSskcookReq putSskcookReq);

  // 슥쿡과 업로드한 사용자 상세 조회
  GetFridgeRecipeRes selectMemberSskcookDetailsBySskcookId(Integer sskcookId);

  List<GetTopSskcookRes> selectTopSskcookList(String yearMonth);
}
