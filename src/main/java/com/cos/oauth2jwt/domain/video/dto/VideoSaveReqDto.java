package com.cos.oauth2jwt.domain.video.dto;

import com.cos.oauth2jwt.domain.video.Video;

import lombok.Data;

@Data
public class VideoSaveReqDto {
	private String title;
	private String vimeoId;
	private boolean isFree;
	
	public Video toEntity() {
		return Video.builder()
				.title(title)
				.vimeoId(vimeoId)
				.isFree(isFree)
				.build();
	}
}
