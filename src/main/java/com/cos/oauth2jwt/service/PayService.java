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
	
	@Transactional(readOnly = true)
	public Pay 결제체크(long courseId, long userId){
		return payRepository.findByUserIdAndCourseId(courseId, userId);
	}
	
	@Transactional
	public Pay 저장하기(Pay pay) {
		return payRepository.save(pay);
	}
	
	@Transactional(readOnly = true)
	public Pay 한건찾기(long payId){
		return payRepository.findById(payId).get();
	}
	
	@Transactional
	public Pay 환불신청(long payId) {
		Pay payEntity = payRepository.findById(payId).get(); // refunding일떄만 가능한 행위. 관리가자 환불했을땐 refunded 이기떄문에. status를 검사
		if(payEntity.getStatus().equals("paid")) {
			payEntity.setStatus("refunding");
		}
		return payEntity;
	}
	
	@Transactional
	public Pay 환불하기(long payId) {
		Pay payEntity = payRepository.findById(payId).get(); // refunding일떄만 가능한 행위. 관리가자 환불했을땐 refunded 이기떄문에. status를 검사
		if(payEntity.getStatus().equals("refunding")) {
			payEntity.setStatus("refunded");
		}
		return payEntity;
	}
	
	@Transactional
	public Pay 환불신청취소(long payId) {
		Pay payEntity = payRepository.findById(payId).get(); // refunding일떄만 가능한 행위. 관리가자 환불했을땐 refunded 이기떄문에. status를 검사
		if(payEntity.getStatus().equals("refunding")) {
			payEntity.setStatus("paid");
			
		}
		return payEntity;
	}
}
