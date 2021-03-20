package com.cos.oauth2jwt.web.faq.dto;

import com.cos.oauth2jwt.domain.faq.Faq;

import lombok.Data;

@Data
public class FaqSaveReqDto {

	private String gubun;
	private String content;
	private String category;
	
	public Faq toEntity() {
		return Faq.builder()
				.gubun(gubun)
				.content(content)
				.category(category)
				.build();
	}
	
}
