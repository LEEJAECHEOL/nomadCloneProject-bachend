package com.cos.oauth2jwt.service;

import org.springframework.stereotype.Service;

import com.cos.oauth2jwt.domain.file.Files;
import com.cos.oauth2jwt.domain.file.FilesRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FilesService {
	private final FilesRepository filesRepository;
	
	public Files save(Files files) {
		Files file = new Files();
		file.setFileName(files.getFileName());
		file.setFileOriName(files.getFileOriName());
		file.setFileUrl(files.getFileUrl());
		
		return filesRepository.save(file);
	}
	public Files 한건찾기(Long id) {
		return filesRepository.findById(id).get();
	}
	public void 삭제하기(Long id) {
		filesRepository.deleteById(id);
	}
}
