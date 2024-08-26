package com.ite.cookeat.domain.longcook.service;

import static com.ite.cookeat.exception.ErrorCode.FILE_UPLOAD_FAIL;
import static com.ite.cookeat.exception.ErrorCode.LONGCOOK_NOT_FOUND;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import com.ite.cookeat.domain.longcook.dto.PostLongcookReq;
import com.ite.cookeat.domain.longcook.mapper.LongcookMapper;
import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.global.dto.Criteria;
import com.ite.cookeat.s3.service.S3UploadService;
import java.io.IOException;
import com.ite.cookeat.global.dto.PaginatedRes;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class LongcookServiceImpl implements LongcookService {

  private final LongcookMapper longcookMapper;
  private final ObjectMapper objectMapper;
  private final S3UploadService s3UploadService;

  @Override
  @Transactional
  public PaginatedRes<GetLongcookRes> findLongcookList(String username, Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(9)
        .pageNum(page)
        .build();
    return PaginatedRes.<GetLongcookRes>builder()
        .cri(cri)
        .total(longcookMapper.selectLongcookListCount(username))
        .data(longcookMapper.selectLongcookList(cri, username))
        .build();
  }

  @Override
  @Transactional
  public PaginatedRes<GetLongcookRes> findRecentLongcookList(Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(9)
        .pageNum(page)
        .build();
    return PaginatedRes.<GetLongcookRes>builder()
        .cri(cri)
        .total(longcookMapper.selectRecentLongcookListCount())
        .data(longcookMapper.selectRecentLongcookList(cri))
        .build();
  }

  @Override
  @Transactional(readOnly = true)
  public GetLongcookRes findLongcook(Integer longcookId) {
    Optional<GetLongcookRes> result = longcookMapper.selectLongcook(longcookId);
    return result.orElseThrow(() -> new CustomException(LONGCOOK_NOT_FOUND));
  }

  @Override
  @Transactional
  public Integer modifyLongcookDeletedate(Integer longcookId) {
    Integer result = longcookMapper.updateLongcookDeletedate(longcookId);
    if (result <= 0) {
      throw new CustomException(LONGCOOK_NOT_FOUND);
    }
    return longcookId;
  }

  @Override
  @Transactional
  public Integer addLongcook(String request, MultipartFile file) {

    String longcookUrl = null;
    PostLongcookReq postLongcookReq = null;

    try {
      postLongcookReq = objectMapper.readValue(request, PostLongcookReq.class);
      longcookUrl = s3UploadService.saveFile(file);
    } catch (IOException e) {
      throw new CustomException(FILE_UPLOAD_FAIL);
    }
    postLongcookReq.setLongcookUrl(longcookUrl);

    longcookMapper.insertLongcook(postLongcookReq);
    return postLongcookReq.getLongcookId();
  }
}
