package com.ite.cookeat.domain.fridge.service;

import static com.ite.cookeat.exception.ErrorCode.INGREDIENT_NOT_FOUND;

import com.ite.cookeat.domain.fridge.dto.GetMemberIngredientsRes;
import com.ite.cookeat.domain.fridge.dto.PostMemberIngredientReq;
import com.ite.cookeat.domain.fridge.mapper.FridgeMapper;
import com.ite.cookeat.domain.member.service.MemberService;
import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.util.SecurityUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * @author 김지수
 * @version 1.0
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.23    김지수       최초 생성, 식재료 조회
 * 2024.08.24    김지수       식재료 등록, 삭제
 * 2024.09.11    김지수       식재료 조회 시 filtering 조건 추가
 * </pre>
 * @since 2024.08.23
 */
@Service
@RequiredArgsConstructor
public class FridgeServiceImpl implements FridgeService {

  private final RestTemplate restTemplate;
  private final MemberService memberService;
  private final FridgeMapper fridgeMapper;

  @Value("${flask.api.url}")
  private String flaskUrl;

  /**
   * 사용자 냉장고 속 식재료 조회
   *
   * @param filtering (유효기간 관련한 filtering 조건)
   * @return 냉장고 속 식재료 목록
   */
  @Override
  @Transactional(readOnly = true)
  public List<GetMemberIngredientsRes> findIngredients(String filtering) {
    return fridgeMapper.selectIngredients(
        memberService.findMemberId(SecurityUtils.getCurrentUsername()), filtering);
  }

  /**
   * 식재료 등록 후, 추천 서버에 가중치 반영 요청
   *
   * @param req (식재료와 이를 등록한 사용자 정보를 담은 DTO)
   * @return 1 이상이면 식재료 등록 요청 성공
   */
  @Override
  @Transactional
  public Integer saveIngredient(PostMemberIngredientReq req) {
    req.setMemberId(memberService.findMemberId(req.getUsername()));
    fridgeMapper.insertIngredient(req);
    restTemplate.postForEntity(flaskUrl + "ingredient", "", String.class);
    return req.getIngredientId();
  }

  /**
   * 식재료 삭제 후, 추천 서버에 가중치 반영 요청
   *
   * @param ingredientId (식재료 ID)
   */
  @Override
  @Transactional
  public void modifyIngredientDeletedate(Integer ingredientId) {
    if (fridgeMapper.updateIngredientDeletedate(ingredientId) <= 0) {
      throw new CustomException(INGREDIENT_NOT_FOUND);
    }
    restTemplate.postForEntity(flaskUrl + "ingredient", "", String.class);
  }
}
