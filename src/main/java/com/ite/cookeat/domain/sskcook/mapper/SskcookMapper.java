package com.ite.cookeat.domain.sskcook.mapper;

import com.ite.cookeat.domain.sskcook.dto.GetSearchRecentSskcookReq;
import com.ite.cookeat.domain.sskcook.dto.GetSearchRecentSskcookRes;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SskcookMapper {
  List<GetSearchRecentSskcookRes> selectSearchRecentSskcook(GetSearchRecentSskcookReq getSearchRecentSskcookReq);
}
