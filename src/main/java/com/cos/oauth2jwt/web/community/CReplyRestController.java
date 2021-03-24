package com.cos.oauth2jwt.web.community;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.oauth2jwt.config.auth.PrincipalDetails;
import com.cos.oauth2jwt.domain.community.CReply;
import com.cos.oauth2jwt.service.CReplyService;
import com.cos.oauth2jwt.web.community.dto.CReplySaveReqDto;
import com.cos.oauth2jwt.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CReplyRestController {
	private final CReplyService cReplyService;
	
	@PostMapping("/cReply")
	public CMRespDto<?> save(@RequestBody CReplySaveReqDto cReplySaveReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		// 데이터 들어오니?	
		System.out.println("들어오는 데이터는 : " + cReplySaveReqDto.toString());
		CReply cReply = cReplySaveReqDto.toEntity();
		cReply.setUser(principalDetails.getUser());
		CReply cReplyEntity = cReplyService.댓글저장(cReply);
		return new CMRespDto<>(HttpStatus.OK.value(), "성공", cReplyEntity);
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
