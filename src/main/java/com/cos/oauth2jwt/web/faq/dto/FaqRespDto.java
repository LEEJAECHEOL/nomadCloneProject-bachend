package com.cos.oauth2jwt.web.faq.dto;

import com.cos.oauth2jwt.domain.faq.Faq;
import com.cos.oauth2jwt.domain.faq.FaqCategory;

import lombok.Data;

@Data
public class FaqRespDto {
	private Long id;
	private String title;
	private String content;
	private FaqCategory faqCategory;
	
	public FaqRespDto(Faq faq) {
		this.id = faq.getId();
		this.title = faq.getTitle();
		this.content = faq.getContent();
		this.faqCategory = faq.getFaqCategory();
	}
}
