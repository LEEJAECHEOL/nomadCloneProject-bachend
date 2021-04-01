package com.cos.oauth2jwt.web.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.cos.oauth2jwt.domain.file.MyFile;
import com.cos.oauth2jwt.domain.tech.Tech;
import com.cos.oauth2jwt.domain.user.User;
import com.cos.oauth2jwt.service.MyFileService;
import com.cos.oauth2jwt.service.UserService;
import com.cos.oauth2jwt.web.auth.dto.LoginRespDto;
import com.cos.oauth2jwt.web.dto.CMRespDto;
import com.cos.oauth2jwt.web.tech.dto.TechSaveReqDto;
import com.cos.oauth2jwt.web.user.dto.UserUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserRestController {

	private final UserService userService;
	private final MyFileService myFileService;
	
	@GetMapping("/user")
	public CMRespDto<?> findAll(){
		List<User> userEntity = userService.유저전체찾기();
		return new CMRespDto<>(HttpStatus.OK.value(), "", userEntity);
	}
	
	@GetMapping("/user/load")
	public CMRespDto<?> loadUser(@AuthenticationPrincipal PrincipalDetails principalDetails){
		User user = principalDetails.getUser();
		LoginRespDto loginRespDto = LoginRespDto.builder().id(user.getId()).name(user.getName()).provider(user.getProvider())
				.email(user.getEmail()).roles(user.getRoles()).build();
		return new CMRespDto<>(HttpStatus.OK.value(), "", loginRespDto);
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
	
	@PostMapping("/profile/{id}")
	public CMRespDto<?> profile(@PathVariable long id, TechSaveReqDto techSaveReqDto, HttpServletRequest request){
		MyFile fileEntity =  myFileService.이미지업로드(techSaveReqDto.getFile(), request);
		int result = userService.프로필수정(fileEntity.getId(), id);
		if(result!=1) {
			throw new IllegalArgumentException();
		}
		return new CMRespDto<>(HttpStatus.CREATED.value(),"성공",null);
	}
	
	@PostMapping("/name/{id}")
	public CMRespDto<?> username(@PathVariable long id, @RequestBody String name){
		String updateName = name.replaceAll("\\\"","");
		int result = userService.이름수정(updateName, id);
		if(result!=1) {
			throw new IllegalArgumentException();
		}
		return new CMRespDto<>(HttpStatus.CREATED.value(),"성공",null);
	}
	
}