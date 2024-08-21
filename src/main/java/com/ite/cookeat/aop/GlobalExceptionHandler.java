package com.ite.cookeat.aop;

import com.ite.cookeat.exception.CustomException;
import com.ite.cookeat.exception.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        log.error("CustomException occurred: {}", e.getErrorCode().getDetail());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    // JWT 관련 예외 처리
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> handleExpiredJwtException(ExpiredJwtException e) {
        log.error("ExpiredJwtException occurred: {}", e.getMessage());
        return ResponseEntity.status(UNAUTHORIZED).body(
                ErrorResponse.builder()
                        .status(UNAUTHORIZED.value())
                        .error(UNAUTHORIZED.name())
                        .msg("만료된 JWT 토큰입니다.")
                        .build()
        );
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ErrorResponse> handleMalformedJwtException(MalformedJwtException e) {
        log.error("MalformedJwtException occurred: {}", e.getMessage());
        return ResponseEntity.status(UNAUTHORIZED).body(
                ErrorResponse.builder()
                        .status(UNAUTHORIZED.value())
                        .error(UNAUTHORIZED.name())
                        .msg("잘못된 형식의 JWT 토큰입니다.")
                        .build()
        );
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedJwtException(UnsupportedJwtException e) {
        log.error("UnsupportedJwtException occurred: {}", e.getMessage());
        return ResponseEntity.status(UNAUTHORIZED).body(
                ErrorResponse.builder()
                        .status(UNAUTHORIZED.value())
                        .error(UNAUTHORIZED.name())
                        .msg("지원되지 않는 JWT 토큰입니다.")
                        .build()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalArgumentException occurred: {}", e.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(
                ErrorResponse.builder()
                        .status(BAD_REQUEST.value())
                        .error(BAD_REQUEST.name())
                        .msg("부적절한 인자가 전달되었습니다.")
                        .build()
        );
    }
}