package com.ite.cookeat.domain.sskcook.service;

import static com.ite.cookeat.exception.ErrorCode.FILE_UPLOAD_FAIL;
import static com.ite.cookeat.exception.ErrorCode.FIND_FAIL_SSKCOOK;
import static com.ite.cookeat.exception.ErrorCode.SSKCOOK_NOT_FOUND;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ite.cookeat.domain.member.service.MemberService;
import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import com.ite.cookeat.domain.sskcook.dto.GetNullSskcookDetailsReq;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookRes;
import com.ite.cookeat.domain.sskcook.dto.GetSskcookDetailsReq;
import com.ite.cookeat.domain.sskcook.dto.GetTotalSskcookDetailsRes;
import com.ite.cookeat.domain.sskcook.dto.PostLikesReq;
import com.ite.cookeat.domain.sskcook.dto.PostSskcookReq;
import com.ite.cookeat.domain.sskcook.dto.PutSskcookReq;
import com.ite.cookeat.domain.sskcook.mapper.SskcookMapper;
import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.exception.ErrorCode;
import com.ite.cookeat.global.dto.Criteria;
import com.ite.cookeat.global.dto.PaginatedRes;
import com.ite.cookeat.s3.service.S3UploadService;
import com.ite.cookeat.util.SecurityUtils;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class SskcookServiceImpl implements SskcookService {

  private final RestTemplate restTemplate;
  private final SskcookMapper sskcookMapper;
  private final S3UploadService s3UploadService;
  private final ObjectMapper objectMapper;
  private final MemberService memberService;

  @Override
  @Transactional
  public PaginatedRes<GetSearchSskcookRes> findSearchRecentSskcookList(
      String keyword, Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .keyword(keyword)
        .build();

    return PaginatedRes.<GetSearchSskcookRes>builder()
        .cri(cri)
        .total(sskcookMapper.selectSearchSskcookListCount(keyword))
        .data(sskcookMapper.selectSearchRecentSskcook(cri, keyword))
        .build();
  }

  @Override
  @Transactional
  public PaginatedRes<GetSearchSskcookRes> findSearchLikesSskcookList(
      String keyword, Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .keyword(keyword)
        .build();
    return PaginatedRes.<GetSearchSskcookRes>builder()
        .cri(cri)
        .total(sskcookMapper.selectSearchSskcookListCount(keyword))
        .data(sskcookMapper.selectSearchLikesSskcookList(cri, keyword))
        .build();
  }

  @Override
  @Transactional
  public PaginatedRes<GetSearchSskcookRes> findRecentSskcookList(Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .build();
    return PaginatedRes.<GetSearchSskcookRes>builder()
        .cri(cri)
        .total(sskcookMapper.selectRecentSskcookListCount())
        .data(sskcookMapper.selectRecentSskcookList(cri))
        .build();
  }

  @Override
  @Transactional
  public PaginatedRes<GetSearchSskcookRes> findMonthlySskcookList(String date, Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .date(date)
        .build();
    return PaginatedRes.<GetSearchSskcookRes>builder()
        .cri(cri)
        .total(sskcookMapper.selectMonthlySskcookListCount(date))
        .data(sskcookMapper.selectMonthlySskcookList(cri, date))
        .build();
  }

  @Override
  @Transactional
  public Integer modifySskcookDeletedate(Integer sskcookId) {
    Integer result = sskcookMapper.updateSskcookDeletedate(sskcookId);
    if (result <= 0) {
      throw new CustomException(SSKCOOK_NOT_FOUND);
    }
    return sskcookId;
  }

  @Override
  @Transactional
  public PaginatedRes<GetSearchSskcookRes> findUserSskcookList(String username, Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .build();
    return PaginatedRes.<GetSearchSskcookRes>builder()
        .cri(cri)
        .total(sskcookMapper.selectUserSskcookListCount(username))
        .data(sskcookMapper.selectUserSskcookList(cri, username))
        .build();
  }

  @Override
  @Transactional
  public PaginatedRes<GetSearchSskcookRes> findTagSskcookList(String tag, Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .tag(tag)
        .build();

    return PaginatedRes.<GetSearchSskcookRes>builder()
        .cri(cri)
        .total(sskcookMapper.selectTagSskcookListCount(tag))
        .data(sskcookMapper.selectTagSskcookList(cri, tag))
        .build();
  }

  @Override
  @Transactional
  public void addLikes(PostLikesReq req) {
    req.setMemberId(memberService.findMemberId(SecurityUtils.getCurrentUsername()));
    int cnt = sskcookMapper.insertLikes(req);

    if (cnt == 0) {
      throw new CustomException(ErrorCode.LIKES_INSERT_FAIL);
    }
  }

  @Override
  @Transactional
  public void removeLikes(PostLikesReq req) {
    req.setMemberId(memberService.findMemberId(SecurityUtils.getCurrentUsername()));
    int cnt = sskcookMapper.deleteLikes(req);

    if (cnt == 0) {
      throw new CustomException(ErrorCode.LIKES_DELETE_FAIL);
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Integer findLikes(PostLikesReq req) {
    req.setMemberId(memberService.findMemberId(SecurityUtils.getCurrentUsername()));
    return sskcookMapper.selectLikesCount(req);
  }

  @Override
  @Transactional
  public void addReport(PostLikesReq postLikesReq) {
    postLikesReq.setMemberId(memberService.findMemberId(SecurityUtils.getCurrentUsername()));
    int cnt = sskcookMapper.insertReport(postLikesReq);

    if (cnt == 0) {
      throw new CustomException(ErrorCode.REPORT_INSERT_FAIL);
    }
  }

  @Override
  @Transactional
  public void removeReport(PostLikesReq postLikesReq) {
    postLikesReq.setMemberId(memberService.findMemberId(SecurityUtils.getCurrentUsername()));
    int cnt = sskcookMapper.deleteReport(postLikesReq);

    if (cnt == 0) {
      throw new CustomException(ErrorCode.REPORT_DELETE_FAIL);
    }
  }

  @Override
  @Transactional(readOnly = true)
  public Integer findReport(PostLikesReq postLikesReq) {
    postLikesReq.setMemberId(memberService.findMemberId(SecurityUtils.getCurrentUsername()));
    return sskcookMapper.selectReportCount(postLikesReq);
  }

  @Override
  @Transactional
  public Integer modifySskcook(String request, MultipartFile file) {
    String sskcookUrl = null;
    PutSskcookReq putSskcookReq = null;

    try {
      putSskcookReq = objectMapper.readValue(request, PutSskcookReq.class);

      // 스-윽쿡 영상이 수정되었을 경우에만 S3에 업로드
      if (file != null && !sskcookMapper.selectSskcookUrl(putSskcookReq.getSskcookId())
          .equals(putSskcookReq.getSskcookUrl())) {
        sskcookUrl = s3UploadService.saveFile(file);
        putSskcookReq.setSskcookUrl(sskcookUrl);
      }

      String ingredientsJson = objectMapper.writeValueAsString(putSskcookReq.getIngredient());
      String hashtagsJson = objectMapper.writeValueAsString(putSskcookReq.getHashtag());
      putSskcookReq.setIngredientsJson(ingredientsJson);
      putSskcookReq.setHashtagsJson(hashtagsJson);
    } catch (IOException e) {
      throw new CustomException(FILE_UPLOAD_FAIL);
    }

    // 프로시저 호출
    sskcookMapper.updateSskcookWithDetails(putSskcookReq);
    return putSskcookReq.getUpdatedCount();

  }

  @Override
  @Transactional(readOnly = true)
  public GetTotalSskcookDetailsRes findSskcookTotalDetails(Integer sskcookId) {

    String username = SecurityUtils.getCurrentUsername();

    if (username == null) {
      GetNullSskcookDetailsReq req = GetNullSskcookDetailsReq.builder()
          .sskcookId(sskcookId)
          .build();
      sskcookMapper.selectNullSskcookDetails(req);
      return GetTotalSskcookDetailsRes.builder()
          .tags(req.getTags())
          .details(req.getDetails())
          .ingredients(req.getIngredients())
          .build();
    }

    GetSskcookDetailsReq req = GetSskcookDetailsReq.builder()
        .username(username)
        .sskcookId(sskcookId)
        .build();

    sskcookMapper.selectSskcookDetails(req);

    return GetTotalSskcookDetailsRes.builder()
        .tags(req.getTags())
        .details(req.getDetails())
        .ingredients(req.getIngredients())
        .build();
  }

  @Override
  @Transactional(readOnly = true)
  public List<GetFridgeRecipeRes> findMyFridgeRecipe() {
    Integer memberId = memberService.findMemberId(SecurityUtils.getCurrentUsername());
    // Flask API의 URL 구성
    String url = "http://localhost:5000/recommend/" + memberId;

    // Flask API 호출 및 JSON 응답 받기
    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

    try {
      // JSON 문자열을 Recommendation 객체 리스트로 변환
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(response.getBody(),
          new TypeReference<>() {
          });
    } catch (Exception e) {
      throw new CustomException(FIND_FAIL_SSKCOOK);
    }
  }

  @Override
  @Transactional
  public Integer addSskcook(String request, MultipartFile file) {

    String sskcookUrl = null;
    PostSskcookReq postSskcookReq = null;

    try {
      postSskcookReq = objectMapper.readValue(request, PostSskcookReq.class);
      sskcookUrl = s3UploadService.saveFile(file);

      String ingredientsJson = objectMapper.writeValueAsString(postSskcookReq.getIngredient());
      String hashtagsJson = objectMapper.writeValueAsString(postSskcookReq.getHashtag());
      postSskcookReq.setIngredientsJson(ingredientsJson);
      postSskcookReq.setHashtagsJson(hashtagsJson);
    } catch (IOException e) {
      throw new CustomException(FILE_UPLOAD_FAIL);
    }
    postSskcookReq.setSskcookUrl(sskcookUrl);
    postSskcookReq.setMemberId(memberService.findMemberId(postSskcookReq.getUsername()));

    // 프로시저 호출
    sskcookMapper.addSskcookWithDetails(postSskcookReq);

    return postSskcookReq.getSskcookId();

  }
}