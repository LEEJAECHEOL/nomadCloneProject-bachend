package com.cos.oauth2jwt.web.community;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.oauth2jwt.domain.community.CReply;
import com.cos.oauth2jwt.domain.community.dto.CReplySaveReqDto;
import com.cos.oauth2jwt.service.CReplyService;
import com.cos.oauth2jwt.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CReplyRestController {
	private final CReplyService cReplyService;
	
	@PostMapping("/cReply")
	public CMRespDto<?> save(@RequestBody CReplySaveReqDto cReplySaveReqDto) {
		return new CMRespDto<>(HttpStatus.OK.value(), "성공", cReplyService.댓글저장(cReplySaveReqDto));
	}
	
	@DeleteMapping("/cReply/{id}")
	public CMRespDto<?> delete(@PathVariable long id){
		cReplyService.댓글삭제(id);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",null);
	}
	
	@GetMapping("/cReply")
	public CMRespDto<?> findAll(){
		List<CReply> cReplysEntity = cReplyService.전체댓글();
		return new CMRespDto<>(1, "성공", cReplysEntity);
	}
	
	@GetMapping("/cReply/{id}")
	public CMRespDto<?> findById(@PathVariable long id){
		CReply cReplyEntity = cReplyService.한건찾기(id);
		return new CMRespDto<>(1,"성공",cReplyEntity);
	}
}
