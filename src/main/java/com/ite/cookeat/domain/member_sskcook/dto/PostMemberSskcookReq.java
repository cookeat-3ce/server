package com.ite.cookeat.domain.member_sskcook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 보관하기 DTO
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
public class PostMemberSskcookReq {

  @Setter
  private Integer memberId;
  private Integer sskcookId;
}
