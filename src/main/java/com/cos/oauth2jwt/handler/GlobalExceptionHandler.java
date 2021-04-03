package com.cos.oauth2jwt.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.cos.oauth2jwt.handler.exception.NoLoginException;
import com.cos.oauth2jwt.web.dto.CMRespDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
//	
	@ExceptionHandler(value=NoLoginException.class)
	public ResponseEntity<?> noLoginException(NoLoginException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
	}
//	
//	@ExceptionHandler(value=MyTokenExpiredException.class)
//	public CMRespDto<?> tokenExpiredException(TokenExpiredException e) {
//		System.out.println("토큰 만료 됨.");
//		return new CMRespDto<>(HttpStatus.UNAUTHORIZED.value(), "세션이 만료되었습니다. 로그인 후 이용해 주세요.", null);
//	}
}