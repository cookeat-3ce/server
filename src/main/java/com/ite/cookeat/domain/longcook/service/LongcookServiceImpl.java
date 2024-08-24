package com.ite.cookeat.domain.longcook.service;

import static com.ite.cookeat.exception.ErrorCode.LONGCOOK_NOT_FOUND;

import com.ite.cookeat.domain.longcook.dto.GetLongCookPageRes;
import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import com.ite.cookeat.domain.longcook.mapper.LongcookMapper;
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

  @Transactional(readOnly = true)
  public GetLongcookRes findLongcook(Integer longcookId) {
    Optional<GetLongcookRes> result = longcookMapper.selectLongcook(longcookId);
    return result.orElseThrow(() -> new CustomException(LONGCOOK_NOT_FOUND));
  }

}
