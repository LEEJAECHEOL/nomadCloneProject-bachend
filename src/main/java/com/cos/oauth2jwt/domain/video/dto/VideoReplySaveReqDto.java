package com.cos.oauth2jwt.domain.video.dto;

import com.cos.oauth2jwt.domain.video.Video;
import com.cos.oauth2jwt.domain.video.VideoReply;

import lombok.Data;

@Data
public class VideoReplySaveReqDto {
	private String content;
	private Long videoId;
	
	public VideoReply toEntity() {
		return VideoReply.builder()
				.content(content)
				.video(Video.builder().id(videoId).build())
				.build();
	}
}
