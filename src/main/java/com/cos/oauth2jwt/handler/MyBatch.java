package com.cos.oauth2jwt.handler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cos.oauth2jwt.domain.error.MyError;
import com.cos.oauth2jwt.handler.exception.ExceptionList;
import com.cos.oauth2jwt.service.MyErrorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MyBatch {
	private final ExceptionList exceptionList;
	private final MyErrorService errorService;
	
	@Scheduled(fixedDelay = 1000*60 * 30) // Cron 정기적 실행
	public void excute() {
		System.out.println("batch is run");
		List<MyError> msg = exceptionList.getData();
		errorService.전체저장(msg);
		exceptionList.clearList();
	}
}
