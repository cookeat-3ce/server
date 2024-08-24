package com.ite.cookeat.domain.member_sskcook.mapper;

import com.ite.cookeat.domain.member_sskcook.dto.PostMemberSskcookReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberSskcookMapper {

  Integer insertMemberSskcook(PostMemberSskcookReq postMemberSskcookReq);

  Integer deleteMemberSskcook(PostMemberSskcookReq postMemberSskcookReq);

  Integer selectMemberSskcookCount(PostMemberSskcookReq postMemberSskcookReq);
}
