package com.ite.cookeat.domain.sskcook.mapper;

import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookRes;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookReq;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SskcookMapper {
  List<GetSearchSskcookRes> selectSearchRecentSskcook(GetSearchSskcookReq getSearchSskcookReq);
  List<GetSearchSskcookRes> selectSearchLikesSskcook(GetSearchSskcookReq getSearchSskcookReq);
}
