package com.cos.oauth2jwt.web.community.dto;

import com.cos.oauth2jwt.domain.community.CReply;
import com.cos.oauth2jwt.domain.community.Community;

import lombok.Data;

@Data
public class CReplySaveReqDto {
	private String content;
	private Long comId;
	
	public CReply toEntity() {
		return CReply.builder()
				.content(content)
				.community(Community.builder().id(comId).build())
				.build();
	}
}
