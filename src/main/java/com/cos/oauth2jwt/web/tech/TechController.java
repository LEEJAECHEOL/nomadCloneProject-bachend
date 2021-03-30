package com.cos.oauth2jwt.web.tech;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cos.oauth2jwt.domain.file.Files;
import com.cos.oauth2jwt.domain.tech.Tech;
import com.cos.oauth2jwt.service.FilesService;
import com.cos.oauth2jwt.service.TechService;
import com.cos.oauth2jwt.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class TechController {
	
	private final TechService techService;
	private final FilesService filesService;
	
	@PostMapping("/tech")
	public CMRespDto<?> save(@RequestParam("file") MultipartFile files, String title, HttpServletRequest req) throws IllegalStateException, IOException{
		
		System.out.println("파일은 : "+files);
		System.out.println("티이틀은 : "+title);
		String defaultPath = req.getSession().getServletContext().getRealPath("/");
		String fileUrl = defaultPath + "images/"; 
		SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SS_");

		Files file = new Files();
		String sourceFileName = files.getOriginalFilename(); 
		String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase(); 

		File destinationFile; 
		String destinationFileName;
		
		do { 
			destinationFileName =format.format(new Date()) + RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension; 
			destinationFile = new File(fileUrl + destinationFileName); 
		} while (destinationFile.exists());
		
		destinationFile.getParentFile().mkdirs(); 
		files.transferTo(destinationFile);
		file.setFileName(destinationFileName);
		file.setFileOriName(sourceFileName);
		file.setFileUrl("http://localhost:8080/images/"+destinationFileName);
		
//		Files fileEntity = filesService.save(file);
//		
//		Tech tech = new Tech();
//		tech.setTitle(title);
//		tech.setFile(fileEntity);
//		
//		Tech TechEntity = techService.save(tech); 
//		return new CMRespDto<>(HttpStatus.OK.value(), "ok" ,TechEntity);
		return null;
	}
}
