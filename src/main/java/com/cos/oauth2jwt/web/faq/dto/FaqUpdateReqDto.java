package com.cos.oauth2jwt.web.faq.dto;

import com.cos.oauth2jwt.domain.faq.Faq;
import com.cos.oauth2jwt.domain.faq.FaqCategory;

import lombok.Data;

@Data
public class FaqUpdateReqDto {
	private String title;
	private String content;
	private Long categoryId;
	
	public Faq toEntity() {
		return Faq.builder()
				.title(title)
				.content(content)
				.faqCategory(FaqCategory.builder().id(categoryId).build())
				.build();
	}
}
