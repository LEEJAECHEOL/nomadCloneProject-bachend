package com.cos.oauth2jwt.service;

import org.springframework.stereotype.Service;

import com.cos.oauth2jwt.domain.pay.Pay;
import com.cos.oauth2jwt.domain.pay.PayRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PayService {
	
	private final PayRepository payRepository;
	
	public Pay 저장하기(Pay pay) {
		return payRepository.save(pay);
	}

}
