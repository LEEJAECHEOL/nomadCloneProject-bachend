package com.cos.oauth2jwt.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cos.oauth2jwt.domain.file.DatabaseFile;
import com.cos.oauth2jwt.domain.file.DatabaseFileRepository;
import com.cos.oauth2jwt.exception.FileNotFoundException;
import com.cos.oauth2jwt.exception.FileStorageException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DatabaseFileService {

	private final DatabaseFileRepository dbFileRepository;

	public DatabaseFile storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			DatabaseFile dbFile = new DatabaseFile(fileName, file.getContentType(), file.getBytes());
			//uuid 생성해서 넣기			
			
			return dbFileRepository.save(dbFile);
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public DatabaseFile getFile(String fileId) {
		return dbFileRepository.findById(fileId)
				.orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
	}

}
