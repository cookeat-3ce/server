package com.ite.cookeat.domain.live.service;

import static com.ite.cookeat.exception.ErrorCode.LIVE_NOT_FOUND;

import com.ite.cookeat.domain.live.dto.GetLiveRes;
import com.ite.cookeat.domain.live.dto.PostLiveReq;
import com.ite.cookeat.domain.live.mapper.LiveMapper;
import com.ite.cookeat.domain.member.service.MemberService;
import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.global.dto.Criteria;
import com.ite.cookeat.global.dto.PaginatedRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LiveServiceImpl implements LiveService {

  private static final int PAGE_SIZE = 9;

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

  @Override
  @Transactional(readOnly = true)
  public PaginatedRes<GetLiveRes> findLiveList(String keyword, Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(PAGE_SIZE)
        .pageNum(page)
        .build();

    return PaginatedRes.<GetLiveRes>builder()
        .cri(cri)
        .total(liveMapper.selectLiveCount(keyword))
        .data(liveMapper.selectLiveListByKeyword(cri, keyword))
        .build();
  }

  @Override
  @Transactional
  public void updateLiveEnddate(Integer liveId) {
    Integer result = liveMapper.modifyLiveEnddate(liveId);
    if (result <= 0) {
      throw new CustomException(LIVE_NOT_FOUND);
    }
  }
}
