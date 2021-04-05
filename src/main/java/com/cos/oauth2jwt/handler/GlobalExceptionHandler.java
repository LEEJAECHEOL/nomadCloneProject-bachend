package com.cos.oauth2jwt.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.cos.oauth2jwt.handler.exception.NoDataException;
import com.cos.oauth2jwt.handler.exception.NoLoginException;
import com.cos.oauth2jwt.web.dto.CMRespDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
//	
	@ExceptionHandler(value=NoLoginException.class)
	public ResponseEntity<?> noLoginException(NoLoginException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value=NoDataException.class)
	public ResponseEntity<?> oDataException(NoDataException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}