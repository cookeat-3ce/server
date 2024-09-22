package com.ite.cookeat.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 멤버 정보 DTO
 *
 * @author 양재혁
 * @version 1.0
 * @since 2024.09.02
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.09.02    양재혁       최초 생성
 * </pre>
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GetUserDetailsReq {

  private String followingname;
  private String followername;

  @Setter
  private String username;
  @Setter
  private String nickname;
  @Setter
  private String profileImage;
  @Setter
  private String oneLiner;
  @Setter
  private Integer subscriptionCount;
  @Setter
  private Integer sskcookCount;
  @Setter
  private String followStatus;

}
