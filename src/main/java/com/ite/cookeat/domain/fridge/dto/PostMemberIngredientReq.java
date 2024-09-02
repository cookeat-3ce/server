package com.ite.cookeat.domain.fridge.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ite.cookeat.util.KSTDateSerializer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostMemberIngredientReq {

  @Setter
  private Integer ingredientId;
  @Setter
  private Integer memberId;
  private String username;
  private String name;
  private String amount;
  private Integer icon;
  @JsonSerialize(using = KSTDateSerializer.class)
  private Date expdate;
}
