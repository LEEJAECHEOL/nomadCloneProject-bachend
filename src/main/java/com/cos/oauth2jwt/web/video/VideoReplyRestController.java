package com.cos.oauth2jwt.web.video;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cos.oauth2jwt.config.auth.PrincipalDetails;
import com.cos.oauth2jwt.domain.video.VideoReply;
import com.cos.oauth2jwt.domain.video.dto.VideoReplySaveReqDto;
import com.cos.oauth2jwt.service.VideoReplyService;
import com.cos.oauth2jwt.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class VideoReplyRestController {				//댓글
	private final VideoReplyService videoReplyService;
	
	@GetMapping("/vReply")
	public CMRespDto<?> findAll(){	//전체 화면에 뿌리기
		return new CMRespDto<>(HttpStatus.OK.value(),"성공", videoReplyService.전체찾기());
	}

	@PostMapping("/vReply")
	public CMRespDto<?> save(@RequestBody VideoReplySaveReqDto videoReplySaveReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		System.out.println("유저정보는 :"+principalDetails.getUser());
		VideoReply videoReply = videoReplySaveReqDto.toEntity();
		videoReply.setUser(principalDetails.getUser());
		VideoReply videoReplyEntity = videoReplyService.한건저장(videoReply);

		// 비디오 댓글데이터 검증.		
		System.out.println(videoReplyEntity.toString());
		return new CMRespDto<>(HttpStatus.OK.value(),"성공", videoReplyEntity);
	} 
	
	@GetMapping("/vReply/{id}")
	public CMRespDto<?> findById(@PathVariable long id){
		VideoReply videoReplyEntity = videoReplyService.한건찾기(id);
		System.out.println(videoReplyEntity.toString());
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",videoReplyEntity);
	}
	
	@DeleteMapping("/vReply/{id}")
	public CMRespDto<?> delete(@PathVariable long id){
		videoReplyService.삭제하기(id);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",null);		//삭제는 null 리턴
	}
}
