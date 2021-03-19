package com.cos.oauth2jwt.domain.courses.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CoursesPreviewRespDto {
	private Long id;
	private String title;
	private String subTitle;
	private String level;
	private Map<String, Object> previewImage = new HashMap<>();
}
