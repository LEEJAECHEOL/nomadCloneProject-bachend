package com.cos.oauth2jwt.domain.community.dto;

import com.cos.oauth2jwt.domain.community.CReply;
import com.cos.oauth2jwt.domain.community.Community;

import lombok.Data;

@Data
public class CReplySaveReqDto {
	private String content;
	private int depth;
	private Long communityId;
	
	public CReply toEntity() {
		return CReply.builder()
				.content(content)
				.depth(depth)
				.community(Community.builder().id(communityId).build())
				.build();
				
	}
}
