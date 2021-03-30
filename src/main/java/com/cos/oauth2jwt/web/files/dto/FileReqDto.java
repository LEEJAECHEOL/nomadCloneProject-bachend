package com.cos.oauth2jwt.web.files.dto;

import javax.imageio.stream.FileCacheImageInputStream;

import org.springframework.web.multipart.MultipartFile;

import com.cos.oauth2jwt.domain.file.Files;
import com.cos.oauth2jwt.domain.user.User;

import lombok.Data;
@Data
public class FileReqDto {
	private MultipartFile file;
	private String fileOriName;
	public Files toEntity(String fileUrl, User userEntity) {
		return Files.builder().fileUrl(fileUrl).user(userEntity).build();
	}
}
