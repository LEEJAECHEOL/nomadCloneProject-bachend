package com.cos.oauth2jwt.web.faq.dto;


import com.cos.oauth2jwt.domain.faq.FaqCategory;

import lombok.Data;

@Data
public class FaqCategorySaveReqDto {
	private String title;
	
	public FaqCategory toEntity() {
		return FaqCategory.builder()
				.title(title)
				.build();
				
	}
}
