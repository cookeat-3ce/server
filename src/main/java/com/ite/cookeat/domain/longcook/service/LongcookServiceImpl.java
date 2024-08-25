package com.ite.cookeat.domain.longcook.service;

import static com.ite.cookeat.exception.ErrorCode.LONGCOOK_NOT_FOUND;

import com.ite.cookeat.domain.longcook.dto.GetLongcookReq;
import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import com.ite.cookeat.domain.longcook.mapper.LongcookMapper;
import com.ite.cookeat.exception.CustomException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LongcookServiceImpl implements LongcookService {

  private final LongcookMapper longcookMapper;

  @Override
  @Transactional(readOnly = true)
  public GetLongcookRes findLongcook(Integer longcookId) {
    Optional<GetLongcookRes> result = longcookMapper.selectLongcook(longcookId);
    return result.orElseThrow(() -> new CustomException(LONGCOOK_NOT_FOUND));
  }
 
  @Override
  @Transactional(readOnly = true)
  public List<GetLongcookRes> findLongcookList(GetLongcookReq getLongcookReq) {
    return longcookMapper.selectLongcookList(getLongcookReq);
  }
}
