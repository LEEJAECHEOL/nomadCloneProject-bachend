package com.cos.oauth2jwt.domain.video.dto;

import java.util.ArrayList;

import com.cos.oauth2jwt.domain.video.Video;

import lombok.Data;

@Data
public class VideoSaveReqDto {
	private String name;
	private String vimeoFolderId;
	
	public Video toEntity() {
		return Video.builder()
				.name(name)
				.vimeoFolderId(vimeoFolderId)
				.contents(new ArrayList<>())
				.build();
	}
}
