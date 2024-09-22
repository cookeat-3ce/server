package com.ite.cookeat.domain.notice.service;

import com.ite.cookeat.domain.notice.dto.PostNoticeReq;

/**
 * 공지 등록, 삭제를 처리하는 Service
 *
 * @author 김지수
 * @version 1.0
 * @since 2024.08.23
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.23    김지수       최초 생성, 공지 등록, 삭제
 * </pre>
 */
public interface NoticeService {

  Integer modifyNoticeDeletedate(Integer noticeId);

  Integer addNotice(PostNoticeReq req);
}
