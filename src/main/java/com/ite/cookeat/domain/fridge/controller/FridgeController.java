package com.ite.cookeat.domain.fridge.controller;

import com.ite.cookeat.domain.fridge.dto.GetMemberIngredientsRes;
import com.ite.cookeat.domain.fridge.dto.PostMemberIngredientReq;
import com.ite.cookeat.domain.fridge.service.FridgeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 사용자 냉장고 (식재료 목록 조회, 등록, 삭제)
 *
 * @author 김지수
 * @version 1.0
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.23    김지수       최초 생성, 식재료 목록 조회 API
 * 2024.08.24    김지수       식재료 등록, 삭제 API
 * 2024.09.11    김지수       식재료 목록 조회
 * </pre>
 * @since 2024.08.23
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fridge")
public class FridgeController {

  private final FridgeService fridgeService;

  @GetMapping
  public ResponseEntity<List<GetMemberIngredientsRes>> ingredientsList(
      @RequestParam(required = false) String filtering) {
    return ResponseEntity.ok(fridgeService.findIngredients(filtering));
  }

  @PostMapping
  public ResponseEntity<Integer> ingredientAdd(@RequestBody PostMemberIngredientReq req) {
    return ResponseEntity.ok(fridgeService.saveIngredient(req));
  }

  @DeleteMapping("/{ingredientId}")
  public ResponseEntity<Integer> ingredientDeletedateModify(@PathVariable Integer ingredientId) {
    fridgeService.modifyIngredientDeletedate(ingredientId);
    return ResponseEntity.noContent().build();
  }
}
