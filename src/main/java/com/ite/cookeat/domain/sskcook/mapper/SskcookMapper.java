package com.ite.cookeat.domain.sskcook.mapper;

import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookRes;
import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SskcookMapper {

  List<GetSearchSskcookRes> selectSearchRecentSskcookList(@Param("cri") Criteria cri,
      @Param("keyword") String keyword);

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
}
