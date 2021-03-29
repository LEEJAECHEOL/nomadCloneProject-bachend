package com.cos.oauth2jwt.web.tech.dto;

import org.springframework.web.multipart.MultipartFile;

import com.cos.oauth2jwt.domain.tech.Tech;

import lombok.Data;

@Data
public class TechSaveReqDto {

	private String name;
	//private MultipartFile techImageFile;
	
	public Tech toEntity() {
		return Tech.builder()
				.name(name)
				//.imageUrl(imageUrl)
				.build();
	}
}
