package com.cos.oauth2jwt.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cos.oauth2jwt.handler.exception.ExceptionList;
import com.cos.oauth2jwt.handler.exception.NoDataException;
import com.cos.oauth2jwt.handler.exception.NoLoginException;
import com.cos.oauth2jwt.handler.exception.NoMeException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private final ExceptionList exceptionList;
	
	SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
	Date time = new Date();
	
	@ExceptionHandler(value=NoLoginException.class)
	public ResponseEntity<?> noLoginException(NoLoginException e) {
		exceptionList.addExceptionList("[" + format.format(time) + "] " + e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(value=NoDataException.class)
	public ResponseEntity<?> oDataException(NoDataException e) {
		exceptionList.addExceptionList("[" + format.format(time) + "] " + e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value=NoMeException.class)
	public ResponseEntity<?> noMeException(NoMeException e) {
		exceptionList.addExceptionList("[" + format.format(time) + "] " + e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}