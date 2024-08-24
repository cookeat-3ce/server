package com.ite.cookeat.domain.longcook.service;

import com.ite.cookeat.domain.longcook.dto.GetLongCookPageRes;
import com.ite.cookeat.domain.longcook.mapper.LongcookMapper;
import com.ite.cookeat.global.dto.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LongcookServiceImpl implements LongcookService {

  private final LongcookMapper longcookMapper;

  @Override
  public GetLongCookPageRes findLongcookList(String username, Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .build();
    return GetLongCookPageRes.builder()
        .cri(cri)
        .total(longcookMapper.selectLongcookListCount(username))
        .longcooks(longcookMapper.selectLongcookList(cri, username))
        .build();
  }
}
