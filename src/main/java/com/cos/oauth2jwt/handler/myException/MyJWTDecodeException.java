package com.cos.oauth2jwt.handler.myException;

public class MyJWTDecodeException extends RuntimeException {
	public MyJWTDecodeException(String msg) {
		super(msg);
	}
}
