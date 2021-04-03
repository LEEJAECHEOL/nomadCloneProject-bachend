package com.cos.oauth2jwt.handler.exception;

public class NoLoginException extends RuntimeException {
	public NoLoginException(String msg) {
		super(msg);
	}
}
