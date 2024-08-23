package com.ite.cookeat.domain.live.service;

import com.ite.cookeat.domain.live.dto.PostLiveReq;
import com.ite.cookeat.domain.live.mapper.LiveMapper;
import com.ite.cookeat.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LiveServiceImpl implements LiveService {

  private final LiveMapper liveMapper;
  private final MemberService memberService;

  @Override
  @Transactional
  public Integer saveLive(PostLiveReq req) {
    req.setMemberId(memberService.findMemberId(req.getUsername()));
    log.info("Insert live : {}", req);
    liveMapper.insertLive(req);
    return req.getLiveId();
  }
}
