package com.ite.cookeat.domain.longcook.service;

import com.ite.cookeat.domain.longcook.dto.GetLongcookReq;
import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import com.ite.cookeat.domain.longcook.mapper.LongcookMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LongcookServiceImpl implements LongcookService {

  private final LongcookMapper longcookMapper;

  @Override
  public List<GetLongcookRes> findLongcook(GetLongcookReq getLongcookReq) {
    return longcookMapper.selectLongcook(getLongcookReq);
  }
}
