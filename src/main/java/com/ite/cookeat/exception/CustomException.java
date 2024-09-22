package com.ite.cookeat.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 커스텀 익셉션
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
@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {

  // 전달할 에러 코드
  private final ErrorCode errorCode;
}