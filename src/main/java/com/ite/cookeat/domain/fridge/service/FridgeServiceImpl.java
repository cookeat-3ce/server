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

@Service
@RequiredArgsConstructor
public class FridgeServiceImpl implements FridgeService {

  private final RestTemplate restTemplate;
  private final MemberService memberService;
  private final FridgeMapper fridgeMapper;

  @Value("${flask.api.url}")
  private String flaskUrl;

  @Override
  @Transactional(readOnly = true)
  public List<GetMemberIngredientsRes> findIngredients(String filtering) {
    return fridgeMapper.selectIngredients(
        memberService.findMemberId(SecurityUtils.getCurrentUsername()), filtering);
  }

  @Override
  @Transactional
  public Integer saveIngredient(PostMemberIngredientReq req) {
    req.setMemberId(memberService.findMemberId(req.getUsername()));
    fridgeMapper.insertIngredient(req);
    restTemplate.postForEntity(flaskUrl + "ingredient", "", String.class);
    return req.getIngredientId();
  }

  @Override
  @Transactional
  public void modifyIngredientDeletedate(Integer ingredientId) {
    if (fridgeMapper.updateIngredientDeletedate(ingredientId) <= 0) {
      throw new CustomException(INGREDIENT_NOT_FOUND);
    }
    restTemplate.postForEntity(flaskUrl + "ingredient", "", String.class);
  }
}
