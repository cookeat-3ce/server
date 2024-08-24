package com.ite.cookeat.domain.sskcook.mapper;

import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookReq;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookRes;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SskcookMapper {

  List<GetSearchSskcookRes> selectSearchRecentSskcook(GetSearchSskcookReq getSearchSskcookReq);

  List<GetSearchSskcookRes> selectSearchLikesSskcook(GetSearchSskcookReq getSearchSskcookReq);

  List<GetSearchSskcookRes> selectRecentSskcook(GetSearchSskcookReq getSearchSskcookReq);

  List<GetSearchSskcookRes> selectMonthlySskcook(GetSearchSskcookReq getSearchSskcookReq);
}
