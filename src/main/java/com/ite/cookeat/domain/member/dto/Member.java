package com.ite.cookeat.domain.member.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 멤버 DTO
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
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Member {

  // 고유번호
  private Integer memberId;
  // 아이디
  private String username;
  // 비밀번호(encrypted)
  private String password;

  private String nickname;

  private String profileImage;

  // 권한 목록(문자열)
  private String roles;


  // 유저의 역할(권한) 문자열에서 리스트 변환
  public List<String> getRoleList() {
    if (this.roles != null && this.roles.length() > 0) {
      return Arrays.asList(this.roles.split(","));
    }
    return new ArrayList();
  }

}