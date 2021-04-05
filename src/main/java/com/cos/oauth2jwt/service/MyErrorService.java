package com.cos.oauth2jwt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cos.oauth2jwt.domain.error.MyError;
import com.cos.oauth2jwt.domain.error.MyErrorRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MyErrorService {
	private final MyErrorRepository errorRepository;
	
	public void 전체저장(List<MyError> data) {
		errorRepository.saveAll(data);
	}
	
}
