package com.ite.cookeat.domain.sskcook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 슥쿡 레시피 추천을 위한 DTO
 *
 * @author 박유진
 * @version 1.0
 * @since 2024.08.23
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.23    박유진       최초 생성
 * 2024.09.10    박유진       프로필 이미지도 반환하도록 수정
 * </pre>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetFridgeRecipeRes {
  private Integer sskcookId;
  private String sskcookUrl;
  private String profileImage;
  private String nickname;
  private String title;
  private String username;
}