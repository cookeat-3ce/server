package com.ite.cookeat.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

  /* code: 400*/
  DUPLICATED_MEMBER(BAD_REQUEST, "중복된 아이디 입니다."),
  FIND_FAIL_SSKCOOK(BAD_REQUEST, "원하는 슥쿡 목록을 불러올 수 없습니다."),

  /* code: 401 */
  MEMBER_NOT_FOUND(UNAUTHORIZED, "회원 정보를 찾을 수 없습니다."),

  /* code: 404 */
  LONGCOOK_NOT_FOUND(NOT_FOUND, "스윽쿡(롱쿡) 정보를 찾을 수 없습니다."),
  NOTICE_NOT_FOUND(NOT_FOUND, "공지 정보를 찾을 수 없습니다.");

  /* code: 409 */
  /* code: 500 */

  private final HttpStatus httpStatus;
  private final String detail;
}