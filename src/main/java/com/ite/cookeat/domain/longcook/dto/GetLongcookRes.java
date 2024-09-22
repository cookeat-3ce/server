package com.ite.cookeat.domain.longcook.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ite.cookeat.util.KSTDateSerializer;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
/**
 * 스윽쿡 상세 조회를 위한 DTO
 *
 * @author 양재혁
 * @version 1.0
 * @since 2024.08.24
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.24    양재혁       최초 생성
 * </pre>
 */
public class GetLongcookRes {

  private Integer longcookId;
  private String username;
  private String nickname;
  private String title;
  private String longcookUrl;
  @JsonSerialize(using = KSTDateSerializer.class)
  private Date regdate;
  private String profileImage;
}
