package com.cos.oauth2jwt.handler.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cos.oauth2jwt.domain.error.MyError;

import lombok.Data;

@Data
@Component
public class ExceptionList {
	public List<MyError> data = new ArrayList<>();
	
	public void addExceptionList(String str) {
		data.add(new MyError().builder().msg(str).build());
	}
	
	public void clearList() {
		data.clear();
	}
}
