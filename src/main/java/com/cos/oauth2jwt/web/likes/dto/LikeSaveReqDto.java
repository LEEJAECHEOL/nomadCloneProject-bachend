package com.cos.oauth2jwt.web.likes.dto;

import com.cos.oauth2jwt.domain.community.Community;
import com.cos.oauth2jwt.domain.likes.Likes;
import com.cos.oauth2jwt.domain.user.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LikeSaveReqDto {
	private Long communityId;
	private Long userId;
	public Likes toEntity() {
		return Likes.builder()
				.community(Community.builder().id(communityId).build())
				.user(User.builder().id(userId).build())
				.build();
	}
}
