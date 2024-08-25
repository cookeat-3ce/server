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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fridge")
public class FridgeController {

  private final FridgeService fridgeService;

  @GetMapping("/{username}")
  public ResponseEntity<List<GetMemberIngredientsRes>> ingredientsList(
      @PathVariable String username) {
    return ResponseEntity.ok(fridgeService.findIngredients(username));
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
