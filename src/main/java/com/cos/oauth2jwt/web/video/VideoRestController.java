package com.cos.oauth2jwt.web.video;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.cos.oauth2jwt.domain.courses.Courses;
import com.cos.oauth2jwt.domain.pay.Pay;
import com.cos.oauth2jwt.domain.user.User;
import com.cos.oauth2jwt.domain.video.Video;
import com.cos.oauth2jwt.domain.video.dto.VideoSaveReqDto;
import com.cos.oauth2jwt.domain.video.dto.VideoSaveRespDto;
import com.cos.oauth2jwt.domain.video.dto.VideoUpdateReqDto;
import com.cos.oauth2jwt.service.CoursesService;
import com.cos.oauth2jwt.service.PayService;
import com.cos.oauth2jwt.service.VideoService;
import com.cos.oauth2jwt.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController			//controller 에 ResponseBody가 추가된것 = Json형태로 객체를 반환!!
public class VideoRestController {
	private final VideoService videoService;
	private final CoursesService coursesService;
	private final PayService payService;
	
	@GetMapping("/video/{id}")
	public CMRespDto<?> userfindById(@PathVariable long id, @AuthenticationPrincipal PrincipalDetails principalDetails){
		Video videoEntity = videoService.한건찾기(id);
		boolean isPay = false; // 결제안함.
		if(principalDetails != null) { // 로그인 한 경우
			User user = principalDetails.getUser(); // 유저정보
			if((user.getRoles()).equals("ROLE_ADMIN")) { // 관리자일때
				isPay = true;
			}else { // 유저 일때
				Courses course = coursesService.비디오로한건가져오기(id); // 해당 비디오 아이디를 가지고있는 코스 정보
				Pay pay = payService.결제체크(course.getId(), user.getId()); // 코스 아이디, 유저아이디를 가지고있는 pay정보
				if(pay != null) {
					System.out.println(pay.getStatus());
					if(pay.getStatus().equals("paid")) {
						isPay = true;
					}
				} 
			}
		}  // 로그인 안한 경우 -> false 
		
		if(!isPay) {
			List<Map<String, Object>> contents = videoEntity.getContents();
			contents.stream().forEach((content)->{ // 강의 리스트
				List<Map<String, Object>> contentItem = (List<Map<String, Object>>) content.get("list");
				contentItem.stream().forEach((item)->{ // 영상 리스트
					Map<String, Object> originItem = item;
					if(!(boolean) originItem.get("isFree")) {
						originItem.replace("vimeoId", ""); // 
					}
				});
			});
		}
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
