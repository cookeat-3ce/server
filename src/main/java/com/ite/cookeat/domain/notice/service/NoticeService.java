package com.ite.cookeat.domain.notice.service;

import com.ite.cookeat.domain.notice.dto.PostNoticeReq;

public interface NoticeService {

  Integer addNotice(PostNoticeReq req);
}
