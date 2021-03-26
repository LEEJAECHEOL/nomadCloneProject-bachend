package com.cos.oauth2jwt.web.user;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	
	@GetMapping("/user/load")
	public CMRespDto<?> loadUser(@AuthenticationPrincipal PrincipalDetails principalDetails){
		System.out.println("load : " + principalDetails);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공", null);
	}
	
	@GetMapping("/user/{id}")
	public CMRespDto<?> findById(@PathVariable Long id){
		User userEntity = userService.유저정보(id);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",userEntity);
	}
	
	// edit profile (name)
	@PutMapping("/user/{id}")
	public CMRespDto<?> findById(@PathVariable Long id, @RequestBody UserUpdateReqDto userUpdateReqDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		User userEntity = userService.프로필수정(id, userUpdateReqDto);
		principalDetails.setUser(userEntity);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",null);
	}
	
	// 회원탈퇴
	@DeleteMapping("/user/{id}")
	public CMRespDto<?> deleteById(@PathVariable Long id) {
		userService.회원탈퇴(id);
		return new CMRespDto<>(HttpStatus.OK.value(),"성공",null);
	}
	
	// image update
	
}