package com.cos.oauth2jwt.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.oauth2jwt.domain.file.MyFile;
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
		userEntity.setName(userUpdateReqDto.getName());
		return userEntity;
	}
	
	@Transactional
	public void 회원탈퇴(long id) {
		userRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public User 유저정보(long id) {
		User userEntity = userRepository.findById(id).get();
		return userEntity;
	}
	
	@Transactional(readOnly = true)
	public List<User> 유저전체찾기() {
		List<User> userEntity = userRepository.findAll();
		return userEntity;
	}
	
	@Transactional
	public int 프로필수정(long fileId, long id) {
		int result = userRepository.updateProfile(fileId, id);
		return result;
	}
	
	@Transactional
	public int 이름수정(String name, long id) {
		int result = userRepository.updateName(name, id);
		return result;
	}
	
}