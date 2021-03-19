package com.cos.oauth2jwt.domain.video.dto;

import lombok.Data;

@Data
public class VideoReplyUpdateReqDto {
	private String content;
	private int depth;
}
