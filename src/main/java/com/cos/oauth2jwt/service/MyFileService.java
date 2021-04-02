package com.cos.oauth2jwt.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.cos.oauth2jwt.config.auth.PrincipalDetails;
import com.cos.oauth2jwt.domain.file.MyFile;
import com.cos.oauth2jwt.domain.file.MyFileRepository;
import com.cos.oauth2jwt.web.files.dto.FileReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MyFileService {
	
	@Value("${file.path}")
	private String uploadFolder;
	
	private final MyFileRepository filesRepository;
	
	@Transactional
	public MyFile 이미지업로드(MultipartFile file, HttpServletRequest request) {
//		System.out.println(request.getLocalAddr()); // ip주소  ipv4로해야댐.
//		System.out.println(request.getLocalPort());
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid + "_" + file.getOriginalFilename();
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
		String fileUrl = "http://localhost:" +  request.getLocalPort() + "/uploads/" + imageFileName;
		try {
			Files.write(imageFilePath, file.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		MyFile myFile = MyFile.builder()
									.fileName(imageFileName)
									.fileOriName(file.getOriginalFilename())
									.imageFilePath(imageFilePath.toString())
									.fileUrl(fileUrl)
									.build();
		
		MyFile filesEntity = filesRepository.save(myFile);
		return filesEntity;
	}
	
	// 안드로이드 이미지 업로드 (안드로이드에서 이미지를 가져왔을때 확장자가 없음)
	@Transactional
	public MyFile 안드로이드이미지업로드(MultipartFile file, HttpServletRequest request) {
//		System.out.println(request.getLocalAddr()); // ip주소  ipv4로해야댐.
//		System.out.println(request.getLocalPort());
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid + "_" + file.getOriginalFilename() +".png";
		System.out.println(file.getOriginalFilename());
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
		System.out.println(imageFilePath);
		String fileUrl = "http://localhost:" +  request.getLocalPort() + "/uploads/" + imageFileName;
		try {
			Files.write(imageFilePath, file.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		MyFile myFile = MyFile.builder()
									.fileName(imageFileName)
									.fileOriName(file.getOriginalFilename())
									.imageFilePath(imageFilePath.toString())
									.fileUrl(fileUrl)
									.build();
		
		MyFile filesEntity = filesRepository.save(myFile);
		return filesEntity;
	}
	
	public MyFile 한건찾기(Long id) {
		return filesRepository.findById(id).get();
	}
	public void 삭제하기(Long id) {
		filesRepository.deleteById(id);
	}
}
