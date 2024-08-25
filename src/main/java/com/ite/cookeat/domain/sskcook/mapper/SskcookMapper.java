package com.ite.cookeat.domain.sskcook.mapper;

import com.ite.cookeat.domain.sskcook.dto.PostHashtagReq;
import com.ite.cookeat.domain.sskcook.dto.PostIngredientReq;
import com.ite.cookeat.domain.sskcook.dto.PostSskcookReq;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookReq;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookRes;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

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

}
