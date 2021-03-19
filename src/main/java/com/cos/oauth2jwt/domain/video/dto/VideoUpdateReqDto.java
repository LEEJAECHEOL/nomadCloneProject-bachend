package com.cos.oauth2jwt.domain.video.dto;

import lombok.Data;

@Data
public class VideoUpdateReqDto {
	private String title;
	private String vimeoId;
	private boolean isFree;
	//toEntity안만드는 이유는 더티 체킹 할 거이기 때문
}
