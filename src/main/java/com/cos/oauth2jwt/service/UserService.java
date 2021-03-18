package com.cos.oauth2jwt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.oauth2jwt.domain.user.User;
import com.cos.oauth2jwt.domain.user.UserRepository;
import com.cos.oauth2jwt.web.user.dto.UserUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	
	@Transactional
	public User 프로필수정(long id, UserUpdateReqDto userUpdateReqDto) {
		User userEntity = userRepository.findById(id).get();
		userEntity.setEmail(userUpdateReqDto.getEmail());
		userEntity.setName(userUpdateReqDto.getName());
		return userEntity;
	}
	
	@Transactional
	public void 회원탈퇴(long id) {
		userRepository.deleteById(id);
	}
}