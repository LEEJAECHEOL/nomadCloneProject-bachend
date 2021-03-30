package com.cos.oauth2jwt.web.tech;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cos.oauth2jwt.domain.file.MyFile;
import com.cos.oauth2jwt.domain.tech.Tech;
import com.cos.oauth2jwt.service.FilesService;
import com.cos.oauth2jwt.service.TechService;
import com.cos.oauth2jwt.web.files.dto.FileReqDto;
import com.cos.oauth2jwt.web.files.dto.FileRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class TechRestController {

	private final TechService techService;
	private final FilesService filesService;

	@PostMapping(value = "/tech",headers = "content-type=multipart/*") 
	public Tech save(@RequestBody @RequestParam("file") MultipartFile file, @RequestBody String title, HttpServletRequest request) {
		System.out.println("파일은?" + file);
		System.out.println("텍스트는?" + title);
		FileReqDto fileReqDto = new FileReqDto();
		fileReqDto.setFile(file);
		MyFile fileEntity =  filesService.이미지업로드(fileReqDto, request);
		FileRespDto fileRespDto = new FileRespDto();
		fileRespDto.setId(fileEntity.getId());
		fileRespDto.setUploaded(true);
		fileRespDto.setUrl(fileEntity.getFileUrl());
		
		Tech tech = new Tech();
		tech.setTitle(title);
		tech.setFile(fileEntity);
		Tech techEntity = techService.save(tech);
		return techEntity;
	}
	

}
