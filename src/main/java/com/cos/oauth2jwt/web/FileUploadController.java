package com.cos.oauth2jwt.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.cos.oauth2jwt.domain.file.DatabaseFile;
import com.cos.oauth2jwt.domain.file.Response;
import com.cos.oauth2jwt.service.DatabaseFileService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class FileUploadController {
	private final DatabaseFileService databaseFileService;
	
	@PostMapping("/uploadFile")
    public Response uploadFile(@RequestParam("file") MultipartFile file) {
        DatabaseFile fileName = databaseFileService.storeFile(file);
        
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/downloadFile/")
            .path(fileName.getFileName())
            .toUriString();

        return new Response(fileName.getFileName(), fileDownloadUri,
            file.getContentType(), file.getSize());
    }
	
	@PostMapping("/uploadMultipleFiles")
    public List< Response > uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
            .stream()
            .map(file -> uploadFile(file))
            .collect(Collectors.toList());
    }
	
//	@GetMapping("/downloadFile/{fileName:.+}")
//    public ResponseEntity < Resource > downloadFile(@PathVariable String fileName, HttpServletRequest request) {
//        // Load file as Resource
//        DatabaseFile databaseFile = databaseFileService.getFile(fileName);
//
//        return ResponseEntity.ok()
//            .contentType(MediaType.parseMediaType(databaseFile.getFileType()))
//            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + databaseFile.getFileName() + "\"")
//            .body(new ByteArrayResource(databaseFile.getData()));
//    }
}
