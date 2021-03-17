package com.cos.oauth2jwt.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.oauth2jwt.domain.faq.Faq;
import com.cos.oauth2jwt.domain.faq.FaqRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FaqService {
	
	private final FaqRepository faqRepository;
	
	@Transactional(readOnly = true)
	public List<Faq> 전체찾기(){
		return faqRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Faq 상세보기(long id) {
		return faqRepository.findById(id).get();
	}
	
	@Transactional
	public Faq 저장하기(Faq faq) {
		return faqRepository.save(faq);
	}
	
}
