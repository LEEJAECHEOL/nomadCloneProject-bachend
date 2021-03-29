package com.cos.oauth2jwt.web.likes.dto;

import com.cos.oauth2jwt.domain.likes.Likes;

import lombok.Data;

@Data
public class LikeSaveReqDto {
	private Long userId;
	private Long communityId;
	
	public Likes toEntity() {
		return Likes.builder()
				.communityId(communityId)
				.userId(userId)
				.build();
	}
}
