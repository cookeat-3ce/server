package com.ite.cookeat.domain.sskcook.mapper;

import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookReq;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookRes;
import com.ite.cookeat.domain.sskcook.dto.PostHashtagReq;
import com.ite.cookeat.domain.sskcook.dto.PostIngredientReq;
import com.ite.cookeat.domain.sskcook.dto.PostLikesReq;
import com.ite.cookeat.domain.sskcook.dto.PostSskcookReq;
import java.util.List;

public interface SskcookMapper {

  Integer insertSskcook(PostSskcookReq req);

  void insertIngredientSskcook(PostIngredientReq req);

  void insertHashtag(PostHashtagReq req);

  void updateSskcookCount(Integer memberId);

  List<GetSearchSskcookRes> selectSearchRecentSskcook(GetSearchSskcookReq getSearchSskcookReq);

  List<GetSearchSskcookRes> selectSearchLikesSskcook(GetSearchSskcookReq getSearchSskcookReq);

  List<GetSearchSskcookRes> selectRecentSskcook(GetSearchSskcookReq getSearchSskcookReq);

  Integer updateSskcookDeletedate(Integer sskcookId);

  List<GetSearchSskcookRes> selectMonthlySskcook(GetSearchSskcookReq getSearchSskcookReq);

  Integer deleteLikes(PostLikesReq postLikesReq);

  Integer insertLikes(PostLikesReq postLikesReq);

  Integer selectLikesCount(PostLikesReq postLikesReq);

}
