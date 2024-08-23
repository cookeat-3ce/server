package com.ite.cookeat.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    /* code: 400*/
    DUPLICATED_MEMBER(BAD_REQUEST, "중복된 아이디 입니다."),
    /* code: 401 */
    MEMBER_NOT_FOUND(UNAUTHORIZED, "회원 정보를 찾을 수 없습니다.");
    /* code: 409 */
    /* code: 500 */

    private final HttpStatus httpStatus;
    private final String detail;
}