package com.cos.oauth2jwt.domain.video.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class VideoUpdateReqDto {
	private List<Map<String, Object>> contents = new ArrayList<>();
}
