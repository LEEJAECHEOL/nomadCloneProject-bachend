package com.cos.oauth2jwt.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.oauth2jwt.domain.pay.Pay;
import com.cos.oauth2jwt.domain.pay.PayRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PayService {
	
	private final PayRepository payRepository;
	
	
	@Transactional(readOnly = true)
	public List<Pay> 전체찾기(){
		return payRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Pay> 유저아이디로찾기(long userId){
		return payRepository.findUserId(userId);
	}
	
	@Transactional
	public Pay 저장하기(Pay pay) {
		return payRepository.save(pay);
	}

}
