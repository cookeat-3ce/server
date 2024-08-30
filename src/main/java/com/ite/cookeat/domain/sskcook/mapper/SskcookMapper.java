package com.ite.cookeat.domain.sskcook.mapper;

import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookRes;
import com.ite.cookeat.domain.sskcook.dto.GetSskcookDetailsReq;
import com.ite.cookeat.domain.sskcook.dto.GetSskcookIngredientsRes;
import com.ite.cookeat.domain.sskcook.dto.PostLikesReq;
import com.ite.cookeat.domain.sskcook.dto.PostSskcookReq;
import com.ite.cookeat.domain.sskcook.dto.PutSskcookReq;
import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SskcookMapper {


  List<GetSearchSskcookRes> selectSearchRecentSskcook(@Param("cri") Criteria cri,
      @Param("keyword") String keyword);

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

  Integer updateSskcookDeletedate(Integer sskcookId);

  Integer deleteLikes(PostLikesReq postLikesReq);

  Integer insertLikes(PostLikesReq postLikesReq);

  Integer selectLikesCount(PostLikesReq postLikesReq);

  List<GetSskcookIngredientsRes> selectSskcookIngredients(Integer sskcookId);

  List<String> selectSskcookTags(Integer sskcookId);

  void selectSskcookDetails(GetSskcookDetailsReq req);
  
  Integer updateSskcookWithDetails(PutSskcookReq putSskcookReq);

  String selectSskcookUrl(Integer sskcookId);
}
