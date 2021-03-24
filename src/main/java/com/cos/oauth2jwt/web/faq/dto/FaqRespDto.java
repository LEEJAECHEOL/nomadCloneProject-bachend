package com.cos.oauth2jwt.web.faq.dto;

import com.cos.oauth2jwt.domain.faq.Faq;

import lombok.Data;

@Data
public class FaqRespDto {
	private Long id;
	private String title;
	private String content;
	
	public FaqRespDto(Faq faq) {
		this.id = faq.getId();
		this.title = faq.getTitle();
		this.content = faq.getContent();
	}
}
