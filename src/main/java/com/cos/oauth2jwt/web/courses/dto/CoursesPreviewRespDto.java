package com.cos.oauth2jwt.web.courses.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	private Long videoId; // 프리뷰 클릭했을때 구매한 코스면 바로 리스트페이지 넘어가서 뿌리기위해 필요
}
