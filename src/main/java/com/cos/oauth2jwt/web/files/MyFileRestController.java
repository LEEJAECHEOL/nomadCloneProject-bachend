package com.cos.oauth2jwt.web.files;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.oauth2jwt.domain.file.MyFile;
import com.cos.oauth2jwt.domain.user.User;
import com.cos.oauth2jwt.service.MyFileService;
import com.cos.oauth2jwt.service.UserService;
import com.cos.oauth2jwt.web.dto.CMRespDto;
import com.cos.oauth2jwt.web.files.dto.FileReqDto;
import com.cos.oauth2jwt.web.files.dto.FileRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MyFileRestController {

	private final MyFileService filesService;
	
	@PostMapping("/upload")
	public FileRespDto fileinsert(FileReqDto fileReqDto, HttpServletRequest request){
		System.out.println(fileReqDto.getFile());
		MyFile fileEntity =  filesService.이미지업로드(fileReqDto.getFile(), request);
		FileRespDto fileRespDto = new FileRespDto();
		fileRespDto.setId(fileEntity.getId());
		fileRespDto.setUploaded(true);
		fileRespDto.setUrl(fileEntity.getFileUrl());
		return fileRespDto;
	}
	
	@DeleteMapping("/upload/{id}")
	public CMRespDto<?> deleteById(@PathVariable Long id, HttpServletRequest req) {
		MyFile fileEntity = filesService.한건찾기(id);
		String fileUrl = fileEntity.getImageFilePath(); 
		
		File deleteFile = new File(fileUrl);
		if(deleteFile.exists()) {
            deleteFile.delete();
            filesService.삭제하기(id);
            return new CMRespDto<>(HttpStatus.OK.value(), "이미지 삭제를 성공하였습니다." ,null);
        } else {
        	return new CMRespDto<>(HttpStatus.BAD_REQUEST.value(), "이미지 삭제를 실패하였습니다." ,null);
        }
		
	}
	
}
