package com.cos.oauth2jwt.web.files;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
//	public FileRespDto fileinsert(@RequestParam("file") MultipartFile files, HttpServletRequest req) throws Exception{
//		String defaultPath = req.getSession().getServletContext().getRealPath("/");
//		String fileUrl = defaultPath + "images/"; 
//		SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SS_");
//
//		Files file = new Files();
//		String sourceFileName = files.getOriginalFilename(); 
//		String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase(); 
//
//		File destinationFile; 
//		String destinationFileName;
//		
//		do { 
//			destinationFileName =format.format(new Date()) + RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension; 
//			destinationFile = new File(fileUrl + destinationFileName); 
//		} while (destinationFile.exists());
//		
//		destinationFile.getParentFile().mkdirs(); 
//		files.transferTo(destinationFile);
//		file.setFileName(destinationFileName);
//		file.setFileOriName(sourceFileName);
//		file.setFileUrl("http://localhost:8100/images/"+destinationFileName);
//		
//		Files fileEntity = filesService.save(file);
//		
//		FileRespDto fileRespDto = new FileRespDto();
//		fileRespDto.setId(fileEntity.getId());
//		fileRespDto.setUploaded(true);
//		fileRespDto.setUrl(fileEntity.getFileUrl());
//		return fileRespDto;
	public CMRespDto<?> fileinsert(FileReqDto fileReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception{
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
