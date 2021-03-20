package com.cos.oauth2jwt.domain.video.dto;

import com.cos.oauth2jwt.domain.video.VideoReply;

import lombok.Data;

@Data
public class VideoReplySaveReqDto {
	private String content;
	private int depth;
	
	public VideoReply toEntity() {
		return VideoReply.builder()
				.content(content)
				.depth(depth)
				.build();
	}
}
