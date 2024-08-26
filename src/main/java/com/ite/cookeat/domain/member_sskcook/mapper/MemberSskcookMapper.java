package com.ite.cookeat.domain.member_sskcook.mapper;

import com.ite.cookeat.domain.member_sskcook.dto.GetMemberSskcookRes;
import com.ite.cookeat.domain.member_sskcook.dto.PostMemberSskcookReq;
import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberSskcookMapper {

  Integer insertMemberSskcook(PostMemberSskcookReq postMemberSskcookReq);

  Integer deleteMemberSskcook(PostMemberSskcookReq postMemberSskcookReq);

  Integer selectMemberSskcookCount(PostMemberSskcookReq postMemberSskcookReq);

  List<GetMemberSskcookRes> selectMemberSskcookList(@Param("cri") Criteria cri,
      @Param("username") String username);

  Integer selectMemberSskcookListCount(String username);
}
