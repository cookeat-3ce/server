package com.ite.cookeat.domain.fridge.service;

import com.ite.cookeat.domain.fridge.dto.GetIngredientsRes;
import com.ite.cookeat.domain.fridge.dto.PostIngredientReq;
import com.ite.cookeat.domain.fridge.mapper.FridgeMapper;
import com.ite.cookeat.domain.member.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FridgeServiceImpl implements FridgeService {

  private final MemberService memberService;
  private final FridgeMapper fridgeMapper;

  @Override
  @Transactional(readOnly = true)
  public List<GetIngredientsRes> findIngredients(String username) {
    return fridgeMapper.selectIngredients(memberService.findMemberId(username));
  }
  
  @Override
  @Transactional
  public Integer saveIngredient(PostIngredientReq req) {
    req.setMemberId(memberService.findMemberId(req.getUsername()));
    fridgeMapper.insertIngredient(req);
    return req.getIngredientId();
  }
}
