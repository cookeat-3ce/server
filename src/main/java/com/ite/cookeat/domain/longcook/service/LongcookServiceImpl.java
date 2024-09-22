package com.ite.cookeat.domain.longcook.service;

import static com.ite.cookeat.exception.ErrorCode.FILE_UPLOAD_FAIL;
import static com.ite.cookeat.exception.ErrorCode.LONGCOOK_NOT_FOUND;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ite.cookeat.domain.longcook.dto.GetLongcookDetailsReq;
import com.ite.cookeat.domain.longcook.dto.GetLongcookRes;
import com.ite.cookeat.domain.longcook.dto.GetNullLongcookDetailsReq;
import com.ite.cookeat.domain.longcook.dto.GetTotalLongcookDetailsRes;
import com.ite.cookeat.domain.longcook.dto.PostLongcookReq;
import com.ite.cookeat.domain.longcook.dto.PutLongcookReq;
import com.ite.cookeat.domain.longcook.mapper.LongcookMapper;
import com.ite.cookeat.domain.member.service.MemberService;
import com.ite.cookeat.domain.sskcook.dto.GetNullSskcookDetailsReq;
import com.ite.cookeat.domain.sskcook.dto.GetSskcookDetailsReq;
import com.ite.cookeat.domain.sskcook.dto.GetTotalSskcookDetailsRes;
import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.global.dto.Criteria;
import com.ite.cookeat.global.dto.PaginatedRes;
import com.ite.cookeat.s3.service.S3UploadService;
import com.ite.cookeat.util.SecurityUtils;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 스윽쿡 등록 및 조회, 수정, 삭제 등을 처리하는 Service
 *
 * @author 박유진
 * @version 1.0
 * @since 2024.08.19
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.19    박유진       최초 생성
 * 2024.08.25    박유진       스윽쿡 삭제
 * 2024.08.26    박유진       슥윽쿡 수정 및 등록
 * 2024.08.26    박유진       슥윽쿡 검색 및 조회
 * 2024.09.02    박유진       스윽쿡 업로드 시 레시피, 재료도 추가
 * 2024.09.02    박유진       스윽쿡 업데이트 시 레시피, 재료도 추가
 * 2024.09.02    박유진       비로그인 상태에서 스윽쿡 조회 가능하도록 수정
 * </pre>
 */
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
  public PaginatedRes<GetLongcookRes> findCreatorLongcookList(String username, Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(PAGE_SIZE)
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
  public PaginatedRes<GetLongcookRes> findRecentLongcookList(String keyword, Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(PAGE_SIZE)
        .pageNum(page)
        .build();
    return PaginatedRes.<GetLongcookRes>builder()
        .cri(cri)
        .total(longcookMapper.selectRecentLongcookListCount(keyword))
        .data(longcookMapper.selectRecentLongcookList(cri, keyword))
        .build();
  }

  /**
   * 스윽쿡 삭제
   *
   * @param longcookId (스윽쿡 아이디)
   * @return 성공적으로 삭제되었을 경우 1반환
   */
  @Override
  @Transactional
  public Integer modifyLongcookDeletedate(Integer longcookId) {
    Integer result = longcookMapper.updateLongcookDeletedate(longcookId);
    if (result <= 0) {
      throw new CustomException(LONGCOOK_NOT_FOUND);
    }
    return longcookId;
  }

  /**
   * 기존 스윽쿡 수정
   *
   * @param request (스윽쿡 수정 요청 JSON)
   * @param file (수정할 스윽쿡 영상)
   * @return 수정된 항목 수
   */
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

  /**
   * 새로운 스윽쿡 등록
   *
   * @param request (스윽쿡 등록 요청 JSON)
   * @param file (스윽쿡 영상)
   * @return 등록된 스윽쿡 ID
   */
  @Override
  @Transactional
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

  /**
   * 스윽쿡 상세 조회
   *
   * @param longcookId (조회할 스윽쿡 아이디)
   * @return 스윽쿡 상세
   */
  @Override
  @Transactional(readOnly = true)
  public GetTotalLongcookDetailsRes findLongcookTotalDetails(Integer longcookId) {

    String username = SecurityUtils.getCurrentUsername();

    if (username == null) {
      GetNullLongcookDetailsReq req = GetNullLongcookDetailsReq.builder()
          .longcookId(longcookId)
          .build();
      longcookMapper.selectNullLongcookDetails(req);
      return GetTotalLongcookDetailsRes.builder()
          .details(req.getDetails())
          .ingredients(req.getIngredients())
          .build();
    }

    GetLongcookDetailsReq req = GetLongcookDetailsReq.builder()
        .username(username)
        .longcookId(longcookId)
        .build();

    longcookMapper.selectLongcookDetails(req);

    return GetTotalLongcookDetailsRes.builder()
        .details(req.getDetails())
        .ingredients(req.getIngredients())
        .build();
  }
}
