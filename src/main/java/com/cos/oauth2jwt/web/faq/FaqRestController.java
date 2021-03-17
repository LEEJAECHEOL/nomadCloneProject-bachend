package com.cos.oauth2jwt.web.faq;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.oauth2jwt.domain.faq.Faq;
import com.cos.oauth2jwt.service.FaqService;
import com.cos.oauth2jwt.web.faq.dto.FaqSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class FaqRestController {
	private final FaqService faqService;
	
	@GetMapping("/faq")
	public List<Faq> findAll() {
		List<Faq> faqs = faqService.전체찾기();
		return faqs;
	}
	
	@GetMapping("/faq/{id}")
	public Faq findById(@PathVariable long id) {
		Faq faqEntity = faqService.상세보기(id);
		return faqEntity;
	}
	
	@PostMapping("/faq")
	public Faq save(@RequestBody FaqSaveReqDto faqSaveReqDto) {
		Faq faq = faqSaveReqDto.toEntity();
		Faq faqEntity = faqService.저장하기(faq);
		return faqEntity;
		
	}
}
