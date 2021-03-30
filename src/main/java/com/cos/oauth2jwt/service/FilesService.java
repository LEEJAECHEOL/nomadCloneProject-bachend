package com.cos.oauth2jwt.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.oauth2jwt.config.auth.PrincipalDetails;
import com.cos.oauth2jwt.domain.file.FilesRepository;
import com.cos.oauth2jwt.web.files.dto.FileReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FilesService {
	private final FilesRepository filesRepository;
	@Value("${file.path}")
	private String uploadFolder;
	
	@Transactional
	public com.cos.oauth2jwt.domain.file.Files 이미지업로드(FileReqDto fileReqDto, PrincipalDetails principalDetails) {
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid+"_"+fileReqDto.getFile().getOriginalFilename();
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		
		try {
			Files.write(imageFilePath, fileReqDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		com.cos.oauth2jwt.domain.file.Files files = fileReqDto.toEntity(imageFileName, principalDetails.getUser());
		com.cos.oauth2jwt.domain.file.Files filesEntity = filesRepository.save(files);
		
		return filesEntity;
	}
	
	public com.cos.oauth2jwt.domain.file.Files 한건찾기(Long id) {
		return filesRepository.findById(id).get();
	}
	public void 삭제하기(Long id) {
		filesRepository.deleteById(id);
	}
}
