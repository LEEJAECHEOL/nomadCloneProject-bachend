package com.cos.oauth2jwt.web.files.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileReqDto {
	private MultipartFile file;
}
