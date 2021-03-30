package com.cos.oauth2jwt.web.files;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cos.oauth2jwt.config.auth.PrincipalDetails;
import com.cos.oauth2jwt.domain.file.Files;
import com.cos.oauth2jwt.service.FilesService;
import com.cos.oauth2jwt.web.dto.CMRespDto;
import com.cos.oauth2jwt.web.files.dto.FileReqDto;
import com.cos.oauth2jwt.web.files.dto.FileRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class FilesRestController {

	private final FilesService filesService;
	
	@PostMapping("/upload")
	public CMRespDto<?> fileinsert(FileReqDto fileReqDto, PrincipalDetails principalDetails) throws Exception{
		Files filesEntity =  filesService.이미지업로드(fileReqDto, principalDetails);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",filesEntity);
	}
	
	@DeleteMapping("/upload/{id}")
	public CMRespDto<?> deleteById(@PathVariable Long id, HttpServletRequest req) {
		String defaultPath = req.getSession().getServletContext().getRealPath("/");
		
		Files fileEntity = filesService.한건찾기(id);
		String fileUrl = defaultPath + "images/" + fileEntity.getFileName(); 
		
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
