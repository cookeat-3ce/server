package com.ite.cookeat.domain.sskcook.mapper;

import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookRes;
import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SskcookMapper {

  List<GetSearchSskcookRes> selectSearchRecentSskcook(@Param("cri") Criteria cri,
      @Param("keyword") String keyword);

  List<GetSearchSskcookRes> selectSearchLikesSskcook(@Param("cri") Criteria cri,
      @Param("keyword") String keyword);

  List<GetSearchSskcookRes> selectRecentSskcook(@Param("cri") Criteria cri);

  List<GetSearchSskcookRes> selectMonthlySskcook(
      @Param("cri") Criteria cri, @Param("date") String date);

  Integer selectSearchSskcookCount(String keyword);

  Integer selectRecentSskcookCount();

  Integer selectMonthlySskcookCount(String data);
}
