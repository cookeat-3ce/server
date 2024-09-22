package com.ite.cookeat.domain.sskcook.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ite.cookeat.util.KSTDateSerializer;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 로그인 시 슥쿡 상세 정보를 받기 위한 DTO
 *
 * @author 양재혁
 * @version 1.0
 * @since 2024.08.26
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.26    양재혁       최초 생성
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GetSskcookDetailsRes {

  private Integer sskcookId;
  private String sskcookUrl;
  private String recipe;
  private String title;
  private String reportStatus;
  private String likeStatus;
  private String storeStatus;
  private String followStatus;
  private String nickname;
  private String username;
  private String profileImage;
  private Integer likeCount;
  @JsonSerialize(using = KSTDateSerializer.class)
  private Date regdate;
}
