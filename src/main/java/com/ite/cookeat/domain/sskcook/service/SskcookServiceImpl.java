package com.ite.cookeat.domain.sskcook.service;

import static com.ite.cookeat.exception.ErrorCode.FILE_UPLOAD_FAIL;
import static com.ite.cookeat.exception.ErrorCode.FIND_FAIL_SSKCOOK;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ite.cookeat.domain.admin.dto.GetTopSskcookRes;
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
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

/**
 * 슥쿡 등록 및 조회, 상세 기능(좋아요, 저장, 신고) 등을 처리하는 Service
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
 * 2024.08.23    박유진       냉장고 속 슥쿡 추천
 * 2024.08.24    박유진       슥쿡 삭제 및 등록
 * 2024.08.27    박유진       슥쿡 수정
 * </pre>
 */
@Service
@RequiredArgsConstructor
public class SskcookServiceImpl implements SskcookService {

  private final RestTemplate restTemplate;
  private final SskcookMapper sskcookMapper;
  private final S3UploadService s3UploadService;
  private final ObjectMapper objectMapper;
  private final MemberService memberService;

  @Value("${flask.api.url}")
  private String flaskUrl;

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
  public void modifySskcookDeletedate(Integer sskcookId) {
    sskcookMapper.updateSskcookDeletedate(sskcookId);
//    if (result <= 0) {
//      throw new CustomException(SSKCOOK_NOT_FOUND);
//    }
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
    req.setAction("like");
    int cnt = sskcookMapper.insertLikes(req);

    if (cnt == 0) {
      throw new CustomException(ErrorCode.LIKES_INSERT_FAIL);
    }
    restTemplate.postForEntity(flaskUrl + "like", req, String.class);
  }

  @Override
  @Transactional
  public void removeLikes(PostLikesReq req) {
    req.setMemberId(memberService.findMemberId(SecurityUtils.getCurrentUsername()));
    req.setAction("unlike");
    int cnt = sskcookMapper.deleteLikes(req);

    if (cnt == 0) {
      throw new CustomException(ErrorCode.LIKES_DELETE_FAIL);
    }
    restTemplate.postForEntity(flaskUrl + "like", req, String.class);
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

  /**
   * 기존 슥쿡 수정
   *
   * @param request (슥쿡 수정 요청 JSON)
   * @param file (수정할 슥쿡 영상)
   * @return 수정된 항목 수
   */
  @Override
  @Transactional
  public Integer modifySskcook(String request, MultipartFile file) {
    String sskcookUrl = null;
    PutSskcookReq putSskcookReq = null;

    try {
      putSskcookReq = objectMapper.readValue(request, PutSskcookReq.class);
      sskcookUrl = s3UploadService.saveFile(file);
      putSskcookReq.setSskcookUrl(sskcookUrl);

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

  /**
   * 냉장고 속 재료로 추천된 슥쿡 레시피 목록 조회
   *
   * @return 추천된 슥쿡 레시피 목록
   */
  @Override
  @Transactional(readOnly = true)
  public List<GetFridgeRecipeRes> findMyFridgeRecipe() {
    Integer memberId = memberService.findMemberId(SecurityUtils.getCurrentUsername());

    ResponseEntity<String> response = restTemplate.getForEntity(flaskUrl + "recommend/" + memberId,
        String.class);

    try {
      ObjectMapper mapper = new ObjectMapper();
      List<Map<String, Object>> recommendListMap = mapper.readValue(response.getBody(),
          new TypeReference<>() {
          });

      return recommendListMap.stream().map(
              map -> sskcookMapper.selectMemberSskcookDetailsBySskcookId(
                  (Integer) map.get("sskcookId")))
          .collect(Collectors.toList());
    } catch (Exception e) {
      throw new CustomException(FIND_FAIL_SSKCOOK);
    }
  }

  /**
   * 새로운 슥쿡 등록
   *
   * @param request (슥쿡 등록 요청 JSON)
   * @param file (슥쿡 영상)
   * @return 등록된 슥쿡 ID
   */
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

  @Override
  public List<GetTopSskcookRes> findTopSskcookList(String yearMonth) {
    return sskcookMapper.selectTopSskcookList(yearMonth);
  }

}