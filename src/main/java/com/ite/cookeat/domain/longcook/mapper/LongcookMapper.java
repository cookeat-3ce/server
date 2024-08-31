package com.ite.cookeat.domain.longcook.mapper;

import com.ite.cookeat.domain.longcook.dto.GetLongcookDetailRes;
import com.ite.cookeat.domain.longcook.dto.PostLongcookReq;
import com.ite.cookeat.domain.longcook.dto.PutLongcookReq;
import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LongcookMapper {

  Optional<GetLongcookDetailRes> selectLongcook(Integer longcookId);

  List<GetLongcookDetailRes> selectLongcookList(@Param("cri") Criteria cri,
      @Param("username") String username);

  Integer selectLongcookListCount(String username);

  List<GetLongcookDetailRes> selectRecentLongcookList(@Param("cri") Criteria cri,
      @Param("keyword") String keyword);

  Integer selectRecentLongcookListCount(@Param("keyword") String keyword);

  Integer updateLongcookDeletedate(Integer longcookId);

  Integer updateLongcook(PutLongcookReq putLongcookReq);

  void insertLongcook(PostLongcookReq postLongcookReq);

  String selectLongcookUrl(Integer longcookId);
}
