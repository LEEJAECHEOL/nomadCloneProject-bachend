package com.cos.oauth2jwt.web.likes.dto;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.oauth2jwt.config.auth.PrincipalDetails;
import com.cos.oauth2jwt.domain.likes.Likes;
import com.cos.oauth2jwt.service.LikeService;
import com.cos.oauth2jwt.web.dto.CMRespDto;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class LikesController {
private final LikeService likeService;
	
	@PostMapping("/like")		// 좋아요 UP
	public CMRespDto<?> save(@RequestBody Long communityId, @AuthenticationPrincipal PrincipalDetails principalDetails){
		LikeSaveReqDto likeSaveReqDto = LikeSaveReqDto.builder().communityId(communityId).userId(principalDetails.getUser().getId()).build();
		Likes like = likeSaveReqDto.toEntity();
		
		LikeClickRespDto respdto = likeService.좋아요(like);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",respdto);
	}

}
