package com.ite.cookeat.domain.notice.service;

import com.ite.cookeat.domain.member.service.MemberService;
import com.ite.cookeat.domain.notice.dto.PostNoticeReq;
import com.ite.cookeat.domain.notice.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

  private final NoticeMapper noticeMapper;
  private final MemberService memberService;

  @Override
  public Integer addNotice(PostNoticeReq req) {
    req.setMemberId(memberService.findMemberId(req.getUsername()));
    noticeMapper.insertNotice(req);
    return req.getNoticeId();
  }
}
