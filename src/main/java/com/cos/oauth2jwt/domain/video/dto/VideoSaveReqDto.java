package com.cos.oauth2jwt.domain.video.dto;

import com.cos.oauth2jwt.domain.video.Video;

import lombok.Data;

@Data
public class VideoSaveReqDto {
	private boolean isHeader;
	private String title;
	private Long folderId;
	
	public Video toEntity() {
		return Video.builder()
				.build();
	}
}
