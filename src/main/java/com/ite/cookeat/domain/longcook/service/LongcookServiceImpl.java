package com.ite.cookeat.domain.longcook.service;

import static com.ite.cookeat.exception.ErrorCode.FILE_UPLOAD_FAIL;
import static com.ite.cookeat.exception.ErrorCode.LONGCOOK_NOT_FOUND;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ite.cookeat.domain.longcook.dto.GetLongcookDetailRes;
import com.ite.cookeat.domain.longcook.dto.PostLongcookReq;
import com.ite.cookeat.domain.longcook.dto.PutLongcookReq;
import com.ite.cookeat.domain.longcook.mapper.LongcookMapper;
import com.ite.cookeat.domain.member.service.MemberService;
import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.global.dto.Criteria;
import com.ite.cookeat.global.dto.PaginatedRes;
import com.ite.cookeat.s3.service.S3UploadService;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class LongcookServiceImpl implements LongcookService {

  private static final int PAGE_SIZE = 9;

  private final LongcookMapper longcookMapper;
  private final MemberService memberSerivce;
  private final S3UploadService s3UploadService;
  private final ObjectMapper objectMapper;

  @Override
  @Transactional
  public PaginatedRes<GetLongcookDetailRes> findCreatorLongcookList(String username, Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(PAGE_SIZE)
        .pageNum(page)
        .build();
    return PaginatedRes.<GetLongcookDetailRes>builder()
        .cri(cri)
        .total(longcookMapper.selectLongcookListCount(username))
        .data(longcookMapper.selectLongcookList(cri, username))
        .build();
  }

  @Override
  @Transactional
  public PaginatedRes<GetLongcookDetailRes> findRecentLongcookList(String keyword, Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(PAGE_SIZE)
        .pageNum(page)
        .build();
    return PaginatedRes.<GetLongcookDetailRes>builder()
        .cri(cri)
        .total(longcookMapper.selectRecentLongcookListCount(keyword))
        .data(longcookMapper.selectRecentLongcookList(cri, keyword))
        .build();
  }

  @Override
  @Transactional(readOnly = true)
  public GetLongcookDetailRes findLongcook(Integer longcookId) {
    Optional<GetLongcookDetailRes> result = longcookMapper.selectLongcook(longcookId);
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
  public Integer modifyLongcook(String request, MultipartFile file) {
    PutLongcookReq putLongcookReq = null;
    String longcookUrl = null;

    try {
      putLongcookReq = objectMapper.readValue(request, PutLongcookReq.class);
      longcookUrl = s3UploadService.saveFile(file);
      putLongcookReq.setLongcookUrl(longcookUrl);

      String ingredientsJson = objectMapper.writeValueAsString(putLongcookReq.getIngredient());
      putLongcookReq.setIngredientsJson(ingredientsJson);
    } catch (IOException e) {
      throw new CustomException(FILE_UPLOAD_FAIL);
    }
    longcookMapper.updateLongcookWithDetails(putLongcookReq);
    return putLongcookReq.getUpdatedCount();
  }


  public Integer addLongcook(String request, MultipartFile file) {

    String longcookUrl = null;
    PostLongcookReq postLongcookReq = null;

    try {
      postLongcookReq = objectMapper.readValue(request, PostLongcookReq.class);
      longcookUrl = s3UploadService.saveFile(file);

      String ingredientsJson = objectMapper.writeValueAsString(postLongcookReq.getIngredient());
      postLongcookReq.setIngredientsJson(ingredientsJson);
    } catch (IOException e) {
      throw new CustomException(FILE_UPLOAD_FAIL);
    }
    postLongcookReq.setLongcookUrl(longcookUrl);
    postLongcookReq.setMemberId(memberSerivce.findMemberId(postLongcookReq.getUsername()));
    longcookMapper.addLongcookWithDetails(postLongcookReq);
    return postLongcookReq.getLongcookId();
  }
}
