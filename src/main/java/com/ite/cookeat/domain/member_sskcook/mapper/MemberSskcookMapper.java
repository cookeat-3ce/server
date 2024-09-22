package com.ite.cookeat.domain.member_sskcook.mapper;

import com.ite.cookeat.domain.member_sskcook.dto.GetMemberSskcookRes;
import com.ite.cookeat.domain.member_sskcook.dto.PostMemberSskcookReq;
import com.ite.cookeat.global.dto.Criteria;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 보관함 관련된 요청을 처리하는 Mybatis Mapper
 *
 * @author 양재혁
 * @version 1.0
 * @since 2024.08.25
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.25    양재혁       최초 생성
 * 2024.08.25    양재혁       보관하기
 * 2024.08.25    양재혁       보관 삭제
 * 2024.08.25    양재혁       보관함 내 여부 조회
 * 2024.08.25    양재혁       보관함 페이징 조회
 * 2024.08.25    양재혁       보관함 페이징 카운트
 * </pre>
 */
@Mapper
public interface MemberSskcookMapper {

  // 보관하기
  Integer insertMemberSskcook(PostMemberSskcookReq postMemberSskcookReq);

  // 보관 삭제
  Integer deleteMemberSskcook(PostMemberSskcookReq postMemberSskcookReq);

  // 보관함 내 여부 조회
  Integer selectMemberSskcookCount(PostMemberSskcookReq postMemberSskcookReq);

  // 보관함 페이징 조회
  List<GetMemberSskcookRes> selectMemberSskcookList(@Param("cri") Criteria cri,
      @Param("username") String username);

  // 보관함 페이징 카운트
  Integer selectMemberSskcookListCount(String username);
}
