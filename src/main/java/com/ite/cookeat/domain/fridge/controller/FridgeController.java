package com.ite.cookeat.domain.fridge.controller;

import com.ite.cookeat.domain.fridge.dto.GetIngredientsRes;
import com.ite.cookeat.domain.fridge.service.FridgeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fridge")
public class FridgeController {

  private final FridgeService fridgeService;

  @GetMapping("/{username}")
  public ResponseEntity<List<GetIngredientsRes>> ingredientsList(@PathVariable String username) {
    return ResponseEntity.ok(fridgeService.findIngredients(username));
  }
}
