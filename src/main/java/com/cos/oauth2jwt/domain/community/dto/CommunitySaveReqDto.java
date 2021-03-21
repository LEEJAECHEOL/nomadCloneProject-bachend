package com.cos.oauth2jwt.domain.community.dto;

import com.cos.oauth2jwt.domain.community.Community;

import lombok.Data;

@Data
public class CommunitySaveReqDto {
	private String title;
	private String content;
	private String category;
	
	public Community toEntity() {
		return Community.builder()
				.title(title)
				.content(content)
				.category(category)
				.build();
				
	}
}