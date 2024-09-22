package com.ite.cookeat.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원 가입 DTO
 *
 * @author 양재혁
 * @version 1.0
 * @since 2024.08.21
 *
 *
 * <pre>
 * 수정일          수정자         내용
 * ------------- ----------- ---------------------------------
 * 2024.08.21    양재혁       최초 생성
 * </pre>
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostSignUpReq {

  private String username;
  private String password;
  private String profileImage;
  private String nickname;
}
