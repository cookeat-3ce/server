package com.ite.cookeat.domain.sskcook.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ite.cookeat.domain.sskcook.dto.GetFridgeRecipeRes;
import com.ite.cookeat.domain.sskcook.dto.GetSearchRecentSskcookReq;
import com.ite.cookeat.domain.sskcook.dto.GetSearchRecentSskcookRes;
import com.ite.cookeat.domain.sskcook.mapper.SskcookMapper;
import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.exception.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class SskcookServiceImpl implements SskcookService {

  private final RestTemplate restTemplate;

  private final SskcookMapper sskcookMapper;

  @Override
  @Transactional(readOnly = true)
  public List<GetSearchRecentSskcookRes> findSearchRecentSskcook(
      GetSearchRecentSskcookReq getSearchRecentSskcookReq) {
    return sskcookMapper.selectSearchRecentSskcook(getSearchRecentSskcookReq);
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
      throw new CustomException(ErrorCode.FIND_FAIL_SSKCOOK);
    }
  }
}