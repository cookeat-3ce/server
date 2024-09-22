package com.ite.cookeat.domain.member_sskcook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 보관함 DTO
 *
 * @author 양재혁
 * @version 1.0
 * @since 2024.08.25
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.25    양재혁       최초 생성
 * </pre>
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GetMemberSskcookRes {

  private Integer sskcookId;
  private String nickname;
  private String sskcookUrl;
  private String title;
  private Integer countLikes;
  private String profileImage;
}
