package com.cos.oauth2jwt.web.user;

import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.oauth2jwt.config.auth.PrincipalDetails;
import com.cos.oauth2jwt.domain.user.User;
import com.cos.oauth2jwt.service.UserService;
import com.cos.oauth2jwt.web.dto.CMRespDto;
import com.cos.oauth2jwt.web.user.dto.UserUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserRestController {

	private final UserService userService;
	
	// edit profile (email, name)
	@PutMapping("/user/{id}")
	public CMRespDto<?> findById(@PathVariable Long id, @RequestBody UserUpdateReqDto userUpdateReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		User userEntity = userService.프로필수정(id, userUpdateReqDto);
		principalDetails.setUser(userEntity);
		return new CMRespDto<>(1,"성공",null);
	}
	
	// 회원탈퇴
	@DeleteMapping("/user/{id}")
	public CMRespDto<?> deleteById(@PathVariable Long id) {
		userService.회원탈퇴(id);
		return new CMRespDto<>(1,"성공",null);
	}
	
	// image update
	
}