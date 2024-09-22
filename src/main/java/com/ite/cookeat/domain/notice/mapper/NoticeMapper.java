package com.ite.cookeat.domain.notice.mapper;

import com.ite.cookeat.domain.notice.dto.PostNoticeReq;

/**
 * 공지와 관련된 요청을 처리하는 Mybatis Mapper
 *
 * @author 김지수
 * @version 1.0
 * @since 2024.08.23
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.23    김지수       최초 생성
 * </pre>
 */
public interface NoticeMapper {

  // 공지 등록
  void insertNotice(PostNoticeReq req);

  // 공지 삭제(deletedate 업데이트)
  Integer updateNoticeDeletedate(Integer noticeId);
}
