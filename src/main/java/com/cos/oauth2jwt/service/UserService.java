package com.cos.oauth2jwt.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.oauth2jwt.domain.file.MyFile;
import com.cos.oauth2jwt.domain.user.User;
import com.cos.oauth2jwt.domain.user.UserRepository;
import com.cos.oauth2jwt.handler.exception.NoDataException;
import com.cos.oauth2jwt.web.user.dto.UserUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User 프로필수정(long id, UserUpdateReqDto userUpdateReqDto) {
        User userEntity = userRepository.findById(id).orElseThrow(()->{
			throw new NoDataException("해당 프로필은 존재하지 않습니다.");
		});
        userEntity.setName(userUpdateReqDto.getName());
        return userEntity;
    }

    @Transactional
    public void 회원탈퇴(long id) {
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public User 유저정보(long id) {
        User userEntity = userRepository.findById(id).orElseThrow(()->{
			throw new NoDataException("해당 프로필은 존재하지 않습니다.");
		});
        return userEntity;
    }

    @Transactional(readOnly = true)
    public List<User> 유저전체찾기() {
        List<User> userEntity = userRepository.findAll();
        return userEntity;
    }

    @Transactional
    public int 프로필수정(long fileId, String fileUrl ,long id) {
        int result = userRepository.updateProfile(fileId, fileUrl, id);
        return result;
    }

    @Transactional
    public User 이름수정(String name, long id) {
        User user = userRepository.findById(id).orElseThrow(()->{
			throw new NoDataException("해당 프로필은 존재하지 않습니다.");
		});
        user.setName(name);
        return user;
    }

}