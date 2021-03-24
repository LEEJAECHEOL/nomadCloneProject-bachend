package com.cos.oauth2jwt.web.video;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.oauth2jwt.domain.video.Video;
import com.cos.oauth2jwt.domain.video.dto.VideoSaveReqDto;
import com.cos.oauth2jwt.domain.video.dto.VideoUpdateReqDto;
import com.cos.oauth2jwt.service.VideoService;
import com.cos.oauth2jwt.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController			//controller 에 ResponseBody가 추가된것 = Json형태로 객체를 반환!!
public class VideoRestController {
	private final VideoService videoService;
	
	@PostMapping("/admin/video")
	public CMRespDto<?> save(@RequestBody Video video) {
		System.out.println(video);
		Video videoEntity = videoService.저장하기(video);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공", videoEntity);
	} 
	
	@GetMapping("/video/{id}")
	public CMRespDto<?> findById(@PathVariable long id){
		Video videoEntity = videoService.한건찾기(id);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",videoEntity);
	}
	
//	@PutMapping("/video/{id}")
//	public CMRespDto<?> update(@PathVariable long id, @RequestBody VideoUpdateReqDto videoUpdateReqDto){
//		Video videoEntity = videoService.수정하기(id,videoUpdateReqDto);
//		return new CMRespDto<>(HttpStatus.OK.value(),"성공",videoEntity);
//	}
	
	@DeleteMapping("/video/{id}")
	public CMRespDto<?> delete(@PathVariable long id){
		videoService.삭제하기(id);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",null);		//삭제는 null 리턴
	}
}
