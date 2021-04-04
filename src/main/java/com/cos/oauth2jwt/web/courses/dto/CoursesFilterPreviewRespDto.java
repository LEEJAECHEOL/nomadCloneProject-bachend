package com.cos.oauth2jwt.web.courses.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CoursesFilterPreviewRespDto {
	private BigInteger id;
	private String previewImage;
	private String title;
	private String subTitle;
	private String level;
	private String tech;
	private String price;
	private BigInteger videoId;
}
