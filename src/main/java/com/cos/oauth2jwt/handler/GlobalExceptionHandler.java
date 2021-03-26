package com.cos.oauth2jwt.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.cos.oauth2jwt.handler.myException.MyJWTDecodeException;
import com.cos.oauth2jwt.web.dto.CMRespDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value=MyJWTDecodeException.class)
	public CMRespDto<?> myReplyException(MyJWTDecodeException e) {
		System.out.println(e);
		return new CMRespDto<>(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), null);
	}
	
	@ExceptionHandler(value=TokenExpiredException.class)
	public CMRespDto<?> myReplyException(TokenExpiredException e) {
		return new CMRespDto<>(HttpStatus.UNAUTHORIZED.value(), "세션이 만료되었습니다. 로그인 후 이용해 주세요.", null);
	}
}
