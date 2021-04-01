package com.cos.oauth2jwt.domain.video.dto;

import com.cos.oauth2jwt.domain.video.VideoReply;

import lombok.Data;

@Data
public class VideoReplySaveReqDto {
	private String content;
	private long videoId;
	
	public VideoReply toEntity() {
		return VideoReply.builder()
				.id(videoId)
				.content(content)
				.build();
	}
}
