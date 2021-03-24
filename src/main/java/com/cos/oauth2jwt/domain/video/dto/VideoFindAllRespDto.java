package com.cos.oauth2jwt.domain.video.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VideoFindAllRespDto {
	private Long id;
	private String name;
	private String vimeoFolderId;
}
