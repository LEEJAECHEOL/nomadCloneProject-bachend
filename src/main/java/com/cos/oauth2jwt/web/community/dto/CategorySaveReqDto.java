package com.cos.oauth2jwt.web.community.dto;

import com.cos.oauth2jwt.domain.community.Category;

import lombok.Data;

@Data
public class CategorySaveReqDto {
	private String title;
	
	public Category toEntity() {
		return Category.builder()
				.title(title)
				.build();
				
	}
}
