package com.cos.oauth2jwt.web.tech.dto;

import com.cos.oauth2jwt.domain.file.MyFile;

import lombok.Data;

@Data
public class TechSaveReqDto{
	private String title;
	private MyFile file;
}
