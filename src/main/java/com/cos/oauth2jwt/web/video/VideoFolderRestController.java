package com.cos.oauth2jwt.web.video;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.oauth2jwt.domain.video.VideoFolder;
import com.cos.oauth2jwt.service.VideoFolderService;
import com.cos.oauth2jwt.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class VideoFolderRestController {
	
	private final VideoFolderService videoFolderService;
	
	@GetMapping("/admin/videoFolder")
	public CMRespDto<?>  findAll(){
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",videoFolderService.전체가져오기());
	}
	
	@PostMapping("/admin/videoFolder")
	public CMRespDto<?> save(@RequestBody VideoFolder videoFolder) {
		VideoFolder videoFolderEntity = videoFolderService.저장하기(videoFolder);
		
		if(videoFolderEntity != null) {
			return new CMRespDto<>(HttpStatus.CREATED.value(),"성공",videoFolderEntity);
		}else {
			return new CMRespDto<>(HttpStatus.BAD_REQUEST.value(),"실패",null);
		}
	}
	
	@DeleteMapping("/admin/videoFolder/{id}")
	public CMRespDto<?>  deleteById(@PathVariable Long id){
		videoFolderService.삭제하기(id);
		return new CMRespDto<>(HttpStatus.OK.value(), "성공", null);
	}
	
	
}
