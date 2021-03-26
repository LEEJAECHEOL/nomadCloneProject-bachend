package com.cos.oauth2jwt.handler.myException;

public class MyTokenExpiredException extends RuntimeException {
	public MyTokenExpiredException(String msg) {
		super(msg);
	}
}
