package com.ite.cookeat.domain.notice.service;

import static com.ite.cookeat.exception.ErrorCode.NOTICE_NOT_FOUND;

import com.ite.cookeat.domain.member.service.MemberService;
import com.ite.cookeat.domain.notice.dto.PostNoticeReq;
import com.ite.cookeat.domain.notice.mapper.NoticeMapper;
import com.ite.cookeat.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

  private final NoticeMapper noticeMapper;
  private final MemberService memberService;

  @Override
  public Integer modifyNoticeDeletedate(Integer noticeId) {
    Integer result = noticeMapper.updateNoticeDeletedate(noticeId);
    if (result <= 0) {
      throw new CustomException(NOTICE_NOT_FOUND);
    }
    return noticeId;
  }

  @Override
  public Integer addNotice(PostNoticeReq req) {
    req.setMemberId(memberService.findMemberId(req.getUsername()));
    noticeMapper.insertNotice(req);
    return req.getNoticeId();
  }
}
