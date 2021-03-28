package com.cos.oauth2jwt.web.video;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.oauth2jwt.config.auth.PrincipalDetails;
import com.cos.oauth2jwt.domain.video.Video;
import com.cos.oauth2jwt.domain.video.dto.VideoSaveReqDto;
import com.cos.oauth2jwt.domain.video.dto.VideoSaveRespDto;
import com.cos.oauth2jwt.domain.video.dto.VideoUpdateReqDto;
import com.cos.oauth2jwt.service.VideoService;
import com.cos.oauth2jwt.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController			//controller 에 ResponseBody가 추가된것 = Json형태로 객체를 반환!!
public class VideoRestController {
	private final VideoService videoService;
	
	@GetMapping("/video/{id}")
	public CMRespDto<?> userfindById(@PathVariable long id, @AuthenticationPrincipal PrincipalDetails principalDetails){
		Video videoEntity = videoService.한건찾기(id);
		// 무료는 다 공개
		// 결제 했는지 안했는지 참조 -> 결제했으면 다 공개
		// 관리자 다 공개
		
		if(videoEntity != null) {
			return new CMRespDto<>(HttpStatus.OK.value(),"성공", videoEntity);
		}else {
			return new CMRespDto<>(HttpStatus.NOT_FOUND.value(), "실패", null);
		}
	}
	
	@PostMapping("/admin/video")
	public CMRespDto<?> save(@RequestBody VideoSaveReqDto videoSaveReqDto) {
		Video videoEntity = videoService.저장하기(videoSaveReqDto.toEntity());
		VideoSaveRespDto respDto = VideoSaveRespDto.builder()
													.id(videoEntity.getId())
													.name(videoEntity.getName())
													.vimeoFolderId(videoEntity.getVimeoFolderId())
													.build();
		return new CMRespDto<>(HttpStatus.OK.value(),"성공", respDto);
	} 
	
	@GetMapping("/admin/video")
	public CMRespDto<?> findAll(){
		List<Video> videosEintity = videoService.전체가져오기();
		
		List<VideoSaveRespDto> respDto = videosEintity.stream().map(v->{
		    return VideoSaveRespDto.builder()
								.id(v.getId())
								.name(v.getName())
								.vimeoFolderId(v.getVimeoFolderId())
								.build();
		}).collect(Collectors.toList());
		System.out.println(respDto);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공", respDto);
	}
	
	
	@GetMapping("/admin/video/{id}")
	public CMRespDto<?> findById(@PathVariable long id, @AuthenticationPrincipal PrincipalDetails principalDetails){
		Video videoEntity = videoService.한건찾기(id);
		if(videoEntity != null) {
			return new CMRespDto<>(HttpStatus.OK.value(),"성공", videoEntity);
		}else {
			return new CMRespDto<>(HttpStatus.NOT_FOUND.value(), "실패", null);
		}
	}
	
	@PutMapping("/admin/video/{id}")
	public CMRespDto<?> update(@PathVariable long id, @RequestBody VideoUpdateReqDto videoUpdateReqDto){
		Video videoEntity = videoService.수정하기(id,videoUpdateReqDto);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",videoEntity);
	}
	
	@DeleteMapping("/admin/video/{id}")
	public CMRespDto<?> delete(@PathVariable long id){
		videoService.삭제하기(id);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",null);		//삭제는 null 리턴
	}
}
