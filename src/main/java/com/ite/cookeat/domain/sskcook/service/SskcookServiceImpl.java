package com.ite.cookeat.domain.sskcook.service;

import static com.ite.cookeat.exception.ErrorCode.FILE_UPLOAD_FAIL;
import static com.ite.cookeat.exception.ErrorCode.FIND_FAIL_SSKCOOK;
import static com.ite.cookeat.exception.ErrorCode.INVALID_JSON;
import static com.ite.cookeat.exception.ErrorCode.SSKCOOK_NOT_FOUND;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ite.cookeat.domain.member.service.MemberService;
import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookPageRes;
import com.ite.cookeat.domain.sskcook.dto.GetSskcookDetailsReq;
import com.ite.cookeat.domain.sskcook.dto.GetSskcookDetailsRes;
import com.ite.cookeat.domain.sskcook.dto.GetSskcookIngredientsRes;
import com.ite.cookeat.domain.sskcook.dto.GetTotalSskcookDetailsRes;
import com.ite.cookeat.domain.sskcook.dto.PostHashtagReq;
import com.ite.cookeat.domain.sskcook.dto.PostLikesReq;
import com.ite.cookeat.domain.sskcook.dto.PostSskcookIngredientReq;
import com.ite.cookeat.domain.sskcook.dto.PostSskcookReq;
import com.ite.cookeat.domain.sskcook.mapper.SskcookMapper;
import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.exception.ErrorCode;
import com.ite.cookeat.global.dto.Criteria;
import com.ite.cookeat.s3.service.S3UploadService;
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
  public GetSearchSskcookPageRes findSearchRecentSskcookList(
      String keyword, Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .keyword(keyword)
        .build();

    return GetSearchSskcookPageRes.builder()
        .cri(cri)
        .total(sskcookMapper.selectSearchSskcookListCount(keyword))
        .sskcooks(sskcookMapper.selectSearchRecentSskcookList(cri, keyword))
        .build();
  }

  @Override
  @Transactional
  public GetSearchSskcookPageRes findSearchLikesSskcookList(
      String keyword, Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .keyword(keyword)
        .build();
    return GetSearchSskcookPageRes.builder()
        .cri(cri)
        .total(sskcookMapper.selectSearchSskcookListCount(keyword))
        .sskcooks(sskcookMapper.selectSearchLikesSskcookList(cri, keyword))
        .build();
  }

  @Override
  @Transactional
  public GetSearchSskcookPageRes findRecentSskcookList(Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .build();
    return GetSearchSskcookPageRes.builder()
        .cri(cri)
        .total(sskcookMapper.selectRecentSskcookListCount())
        .sskcooks(sskcookMapper.selectRecentSskcookList(cri))
        .build();
  }

  @Override
  @Transactional
  public GetSearchSskcookPageRes findMonthlySskcookList(String date, Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .date(date)
        .build();
    return GetSearchSskcookPageRes.builder()
        .cri(cri)
        .total(sskcookMapper.selectMonthlySskcookListCount(date))
        .sskcooks(sskcookMapper.selectMonthlySskcookList(cri, date))
        .build();
  }

  @Override
  public Integer modifySskcookDeletedate(Integer sskcookId) {
    Integer result = sskcookMapper.updateSskcookDeletedate(sskcookId);
    if (result <= 0) {
      throw new CustomException(SSKCOOK_NOT_FOUND);
    }
    return sskcookId;
  }

  @Override
  @Transactional
  public GetSearchSskcookPageRes findUserSskcookList(String username, Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .build();
    return GetSearchSskcookPageRes.builder()
        .cri(cri)
        .total(sskcookMapper.selectUserSskcookListCount(username))
        .sskcooks(sskcookMapper.selectUserSskcookList(cri, username))
        .build();
  }

  @Override
  @Transactional
  public GetSearchSskcookPageRes findTagSskcookList(String tag, Integer page) {
    Criteria cri = Criteria.builder()
        .pageSize(10)
        .pageNum(page)
        .tag(tag)
        .build();

    return GetSearchSskcookPageRes.builder()
        .cri(cri)
        .total(sskcookMapper.selectTagSskcookListCount(tag))
        .sskcooks(sskcookMapper.selectTagSskcookList(cri, tag))
        .build();
  }

  @Override
  public void addLikes(String username, Integer sskcookId) {
    PostLikesReq modifiedReq = PostLikesReq.builder()
        .memberId(memberService.findMemberId(username))
        .sskcookId(sskcookId)
        .build();
    int cnt = sskcookMapper.insertLikes(modifiedReq);

    if (cnt == 0) {
      throw new CustomException(ErrorCode.LIKES_INSERT_FAIL);
    }
  }

  @Override
  public void removeLikes(String username, Integer sskcookId) {
    PostLikesReq modifiedReq = PostLikesReq.builder()
        .memberId(memberService.findMemberId(username))
        .sskcookId(sskcookId)
        .build();
    int cnt = sskcookMapper.deleteLikes(modifiedReq);

    if (cnt == 0) {
      throw new CustomException(ErrorCode.LIKES_DELETE_FAIL);
    }
  }

  @Override
  public Integer findLikes(String username, Integer sskcookId) {
    PostLikesReq modifiedReq = PostLikesReq.builder()
        .memberId(memberService.findMemberId(username))
        .sskcookId(sskcookId)
        .build();
    return sskcookMapper.selectLikesCount(modifiedReq);
  }

  @Override
  @Transactional
  public GetTotalSskcookDetailsRes findSskcookTotalDetails(String username, Integer sskcookId)
      throws IOException {
    JsonNode jsonNode = objectMapper.readTree(username);
    String name = jsonNode.get("username").asText();
    GetSskcookDetailsReq getSskcookDetailsReq = GetSskcookDetailsReq.builder()
        .username(name)
        .sskcookId(sskcookId)
        .build();
    System.out.println(username + " " + sskcookId);
    List<String> tags = sskcookMapper.selectSskcookTags(sskcookId);
    GetSskcookDetailsRes details = sskcookMapper.selectSskcookDetails(getSskcookDetailsReq);
    List<GetSskcookIngredientsRes> ingredients = sskcookMapper.selectSskcookIngredients(sskcookId);
    return GetTotalSskcookDetailsRes.builder()
        .details(details)
        .tags(tags)
        .ingredients(ingredients)
        .build();
  }

  @Override
  @Transactional(readOnly = true)
  public List<GetFridgeRecipeRes> findMyFridgeRecipe(String username) {

    // Flask API의 URL 구성
    String url = "http://localhost:5000/recommend/" + username;

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
    } catch (IOException e) {
      throw new CustomException(INVALID_JSON);
    }

    try {
      sskcookUrl = s3UploadService.saveFile(file);
    } catch (IOException e) {
      throw new CustomException(FILE_UPLOAD_FAIL);
    }
    postSskcookReq.setSskcookUrl(sskcookUrl);

    // 정상적으로 슥쿡이 업로드 되었을 경우
    if (sskcookMapper.insertSskcook(postSskcookReq) == 1) {

      // 해당 회원의 슥쿡 카운트 증가
      sskcookMapper.updateSskcookCount(postSskcookReq.getMemberId());
    }
    Integer sskcookId = postSskcookReq.getSskcookId();

    List<PostSskcookIngredientReq> ingredients = postSskcookReq.getIngredient();
    if (ingredients != null && !ingredients.isEmpty()) {
      for (PostSskcookIngredientReq ingredient : ingredients) {
        ingredient.setSskcookId(sskcookId);
        sskcookMapper.insertIngredientSskcook(ingredient);
      }
    }

    List<PostHashtagReq> hashtags = postSskcookReq.getHashtag();
    if (hashtags != null && !hashtags.isEmpty()) {
      for (PostHashtagReq hashtag : hashtags) {
        hashtag.setSskcookId(sskcookId);
        sskcookMapper.insertHashtag(hashtag);
      }
    }
    return sskcookId;

  }
}