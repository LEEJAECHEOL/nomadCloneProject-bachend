package com.cos.oauth2jwt.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.oauth2jwt.domain.faq.FaqCategory;
import com.cos.oauth2jwt.domain.faq.FaqCategoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FaqCategoryService {
	
	private final FaqCategoryRepository faqCategoryRepository;
	
	@Transactional
	public FaqCategory 카테고리저장(FaqCategory faqCategory) {
		FaqCategory faqCategoryEntity = faqCategoryRepository.save(faqCategory);
		return faqCategoryEntity;
	} 
	
	@Transactional(readOnly = true)
	public List<FaqCategory> 전체찾기(){
		List<FaqCategory> faqCategoryEntity = faqCategoryRepository.findAll();
		return faqCategoryEntity;
	}
	
	@Transactional
	public void 삭제하기(long id) {
		faqCategoryRepository.deleteById(id);
	}
	
}
