package com.ite.cookeat.domain.notice.mapper;

import com.ite.cookeat.domain.notice.dto.PostNoticeReq;

public interface NoticeMapper {

  void insertNotice(PostNoticeReq req);

  Integer updateNoticeDeletedate(Integer noticeId);
}
