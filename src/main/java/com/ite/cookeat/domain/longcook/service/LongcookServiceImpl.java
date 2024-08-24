package com.ite.cookeat.domain.longcook.service;

import static com.ite.cookeat.exception.ErrorCode.LONGCOOK_NOT_FOUND;

import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import com.ite.cookeat.domain.longcook.mapper.LongcookMapper;
import com.ite.cookeat.domain.sskcook.dto.GetLongcookPageRes;
import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.global.dto.Criteria;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LongcookServiceImpl implements LongcookService {

  private final LongcookMapper longcookMapper;

  @Override
  public GetLongcookPageRes findLongcookList(String username, Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(9)
        .pageNum(page)
        .build();
    return GetLongcookPageRes.builder()
        .cri(cri)
        .total(longcookMapper.selectLongcookListCount(username))
        .longcooks(longcookMapper.selectLongcookList(cri, username))
        .build();
  }

  @Override
  public GetLongcookPageRes findRecentLongcookList(Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(9)
        .pageNum(page)
        .build();
    return GetLongcookPageRes.builder()
        .cri(cri)
        .total(longcookMapper.selectRecentLongcookListCount())
        .longcooks(longcookMapper.selectRecentLongcookList(cri))
        .build();
  }

  @Transactional(readOnly = true)
  public GetLongcookRes findLongcook(Integer longcookId) {
    Optional<GetLongcookRes> result = longcookMapper.selectLongcook(longcookId);
    return result.orElseThrow(() -> new CustomException(LONGCOOK_NOT_FOUND));
  }

}
