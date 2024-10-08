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
 * 2024.08.24    양재혁       최신 순 슥쿡 검색 페이징 조회
 * 2024.08.24    양재혁       좋아요 순 슥쿡 검색 페이징 조회
 * 2024.08.24    양재혁       최신 순 슥쿡 리스트 페이징 조회
 * 2024.08.24    양재혁       저번 달 좋아요 순 슥쿡 리스트 페이징 조회
 * 2024.08.25    양재혁       멤버 별 슥쿡 리스트 페이징 조회
 * 2024.08.25    양재혁       태그 별 슥쿡 리스트 페이징 조회
 * 2024.08.25    양재혁       좋아요
 * 2024.08.25    양재혁       좋아요 취소
 * 2024.08.27    박유진       슥쿡 수정
 * 2024.08.30    양재혁       슥쿡 상세 정보 조회
 * 2024.08.31    양재혁       신고
 * 2024.08.31    양재혁       신고 취소
 * 2024.08.31    양재혁       신고 조회
 * 2024.09.10    박유진       좋아요 시 추천 모델 업데이트 되도록 수정
 * 2024.09.12    양재혁       슥쿡 삭제 시 MEMBER테이블 내 슥쿡 개수 -1
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

  /**
   * 최신 순 슥쿡 검색 페이징 조회
   *
   * @param keyword
   * @param page
   * @return 최신 순 슥쿡 리스트 페이징 데이터
   */
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

  /**
   * 좋아요 순 슥쿡 검색 페이징 조회
   *
   * @param keyword
   * @param page
   * @return 좋아요 순 슥쿡 리스트 페이징 데이터
   */
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

  /**
   * 최신 순 슥쿡 리스트 페이징 조회
   *
   * @param page
   * @return 최신 순 슥쿡 리스트 페이징 데이터
   */
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

  /**
   * 저번 달 좋아요 순 슥쿡 리스트 페이징 조회
   *
   * @param date
   * @param page
   * @return 저번 달 좋아요 순 슥쿡 리스트 페이징 데이터
   */
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

  /**
   * 슥쿡 삭제 시 MEMBER테이블 내 슥쿡 개수 -1
   *
   * @param sskcookId
   */
  @Override
  @Transactional
  public void modifySskcookDeletedate(Integer sskcookId) {
    sskcookMapper.updateSskcookDeletedate(sskcookId);
//    if (result <= 0) {
//      throw new CustomException(SSKCOOK_NOT_FOUND);
//    }
  }

  /**
   * 멤버 별 슥쿡 리스트 페이징 조회
   *
   * @param username
   * @param page
   * @return 멤버 별 슥쿡 리스트 페이징 데이터
   */
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

  /**
   * 태그 별 슥쿡 리스트 페이징 조회
   *
   * @param tag
   * @param page
   * @return 태그 별 슥쿡 리스트 페이징 데이터
   */
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

  /**
   * 좋아요
   *
   * @param req
   */
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

  /**
   * 좋아요 취소
   *
   * @param req
   */
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

  /**
   * 신고
   *
   * @param postLikesReq
   */
  @Override
  @Transactional
  public void addReport(PostLikesReq postLikesReq) {
    postLikesReq.setMemberId(memberService.findMemberId(SecurityUtils.getCurrentUsername()));
    int cnt = sskcookMapper.insertReport(postLikesReq);

    if (cnt == 0) {
      throw new CustomException(ErrorCode.REPORT_INSERT_FAIL);
    }
  }

  /**
   * 신고 취소
   *
   * @param postLikesReq
   */
  @Override
  @Transactional
  public void removeReport(PostLikesReq postLikesReq) {
    postLikesReq.setMemberId(memberService.findMemberId(SecurityUtils.getCurrentUsername()));
    int cnt = sskcookMapper.deleteReport(postLikesReq);

    if (cnt == 0) {
      throw new CustomException(ErrorCode.REPORT_DELETE_FAIL);
    }
  }

  /**
   * 신고 조회
   *
   * @param postLikesReq
   * @return 신고한 횟수
   */
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
   * @param file    (수정할 슥쿡 영상)
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

  /**
   * 슥쿡 상세 정보 조회
   *
   * @param sskcookId
   * @return 슥쿡 상세 정보 데이터
   */
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
   * @param file    (슥쿡 영상)
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

  /**
   * 새로운 슥쿡 등록
   *
   * @param yearMonth 상위 슥쿡 조회
   * @return 상위 슥쿡 데이터
   */
  @Override
  public List<GetTopSskcookRes> findTopSskcookList(String yearMonth) {
    return sskcookMapper.selectTopSskcookList(yearMonth);
  }

}