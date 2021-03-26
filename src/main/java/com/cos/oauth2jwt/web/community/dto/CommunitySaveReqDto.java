package com.cos.oauth2jwt.web.community.dto;

import com.cos.oauth2jwt.domain.community.Category;
import com.cos.oauth2jwt.domain.community.Community;

import lombok.Data;

@Data
public class CommunitySaveReqDto {
	private String title;
	private String content;
	private Long categoryId;
	
	public Community toEntity() {
		return Community.builder()
				.title(title)
				.content(content)
				.category(Category.builder().id(categoryId).build())
				.build();
				
	}
}
