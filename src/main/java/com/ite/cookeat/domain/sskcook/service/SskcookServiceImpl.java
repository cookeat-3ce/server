package com.ite.cookeat.domain.sskcook.service;

import static com.ite.cookeat.exception.ErrorCode.FILE_UPLOAD_FAIL;
import static com.ite.cookeat.exception.ErrorCode.FIND_FAIL_SSKCOOK;
import static com.ite.cookeat.exception.ErrorCode.INVALID_JSON;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import com.ite.cookeat.domain.sskcook.dto.PostHashtagReq;
import com.ite.cookeat.domain.sskcook.dto.PostIngredientReq;
import com.ite.cookeat.domain.sskcook.dto.PostSskcookReq;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookReq;
import com.ite.cookeat.domain.sskcook.dto.GetSearchSskcookRes;
import com.ite.cookeat.domain.sskcook.mapper.SskcookMapper;
import com.ite.cookeat.exception.CustomException;
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

  private final SskcookMapper sskcookMapper;

  @Override
  @Transactional(readOnly = true)
  public List<GetSearchSskcookRes> findSearchRecentSskcook(
      GetSearchSskcookReq getSearchSskcookReq) {
    return sskcookMapper.selectSearchRecentSskcook(getSearchSskcookReq);
  }

  @Override
  @Transactional(readOnly = true)
  public List<GetSearchSskcookRes> findSearchLikesSskcook(
      GetSearchSskcookReq getSearchSskcookReq) {
    return sskcookMapper.selectSearchLikesSskcook(getSearchSskcookReq);
  }

  @Override
  @Transactional(readOnly = true)
  public List<GetSearchSskcookRes> findRecentSskcook(GetSearchSskcookReq getSearchSskcookReq) {
    return sskcookMapper.selectRecentSskcook(getSearchSskcookReq);
  }

  @Override
  public List<GetSearchSskcookRes> findMonthlySskcook(GetSearchSskcookReq getSearchSskcookReq) {
    return sskcookMapper.selectMonthlySskcook(getSearchSskcookReq);
  }

  @Override
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

    List<PostIngredientReq> ingredients = postSskcookReq.getIngredient();
    if (ingredients != null && !ingredients.isEmpty()) {
      for (PostIngredientReq ingredient : ingredients) {
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