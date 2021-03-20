package com.cos.oauth2jwt.domain.community.dto;

import com.cos.oauth2jwt.domain.community.CReply;
import lombok.Data;

@Data
public class CReplySaveReqDto {
	private String content;
	private int depth;
	public CReply toEntity() {
		return CReply.builder()
				.content(content)
				.depth(depth)
				.build();
				
	}
}
