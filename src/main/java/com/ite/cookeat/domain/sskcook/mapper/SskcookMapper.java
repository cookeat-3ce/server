package com.ite.cookeat.domain.sskcook.mapper;

import com.ite.cookeat.domain.sskcook.dto.PostHashtagReq;
import com.ite.cookeat.domain.sskcook.dto.PostIngredientReq;
import com.ite.cookeat.domain.sskcook.dto.PostSskcookReq;

public interface SskcookMapper {

  Integer insertSskcook(PostSskcookReq req);

  void insertIngredientSskcook(PostIngredientReq req);

  void insertHashtag(PostHashtagReq req);

  void updateSskcookCount(Integer memberId);
}
