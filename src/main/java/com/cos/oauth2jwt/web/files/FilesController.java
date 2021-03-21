package com.cos.oauth2jwt.web.files;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cos.oauth2jwt.domain.file.Files;
import com.cos.oauth2jwt.service.FilesService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class FilesController {

	private final FilesService filesService;
	private final ResourceLoader resourceLoader;
	
	@PostMapping("/upload")
	public String fileinsert(@RequestParam("file") MultipartFile files) throws Exception{
		Files file = new Files();
		
		String sourceFileName = files.getOriginalFilename(); 
        		String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase(); 
        		File destinationFile; 
        		String destinationFileName;
        		
        		String fileUrl = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\"; 
        		
        		do { 
        			destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension; 
        			destinationFile = new File(fileUrl + destinationFileName); 
        		} while (destinationFile.exists());
        		
        		destinationFile.getParentFile().mkdirs(); 
        		files.transferTo(destinationFile);
        		
        		file.setFileName(destinationFileName);
        		file.setFileOriName(sourceFileName);
        		file.setFileUrl("/images/"+sourceFileName);
        		filesService.save(file);
			return "ok";
	}
	
//	@GetMapping("/download")
}
