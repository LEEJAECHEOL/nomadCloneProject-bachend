package com.cos.oauth2jwt.web.video;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.oauth2jwt.domain.user.User;
import com.cos.oauth2jwt.domain.video.VideoReply;
import com.cos.oauth2jwt.domain.video.dto.VideoReplySaveReqDto;
import com.cos.oauth2jwt.domain.video.dto.VideoReplyUpdateReqDto;
import com.cos.oauth2jwt.service.VideoReplyService;
import com.cos.oauth2jwt.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class VideoReplyRestController {				//댓글
	private final VideoReplyService videoReplyService;
	
	@GetMapping("/vreply")
	public CMRespDto<?> findAll(){	//전체 화면에 뿌리기
		return new CMRespDto<>(HttpStatus.OK.value(),"성공", videoReplyService.전체찾기());
	}
	
	
	@PostMapping("/vreply")
	public CMRespDto<?> save(@RequestBody VideoReplySaveReqDto videoReplySaveReqDto) {
		VideoReply videoReplyEntity = videoReplyService.한건저장(videoReplySaveReqDto);
//		videoReplyEntity.setUser(new User(1L,"ssar","1234","ssar@nate.com","ssar","USER","Image",new Timestamp(System.currentTimeMillis())));
		return new CMRespDto<>(HttpStatus.OK.value(),"성공", videoReplyEntity);
	} 
	
	@GetMapping("/vreply/{id}")
	public CMRespDto<?> findById(@PathVariable long id){
		VideoReply videoReplyEntity = videoReplyService.한건찾기(id);
		System.out.println(videoReplyEntity.toString());
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",videoReplyEntity);
	}
	
	@PutMapping("/vreply/{id}")
	public CMRespDto<?> update(@PathVariable long id, @RequestBody VideoReplyUpdateReqDto videoReplyUpdateReqDto){
		VideoReply videoReplyEntity = videoReplyService.수정하기(id,videoReplyUpdateReqDto);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",videoReplyEntity);
	}
	
	@DeleteMapping("/vreply/{id}")
	public CMRespDto<?> delete(@PathVariable long id){
		videoReplyService.삭제하기(id);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",null);		//삭제는 null 리턴
	}
}
