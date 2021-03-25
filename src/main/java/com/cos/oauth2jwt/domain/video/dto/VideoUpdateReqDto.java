package com.cos.oauth2jwt.domain.video.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class VideoUpdateReqDto {
	private List<String> contents = new ArrayList<>();
	private List<List<Map<String, Object>>> contentList = new ArrayList<>();
}
