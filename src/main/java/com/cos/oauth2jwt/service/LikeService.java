package com.cos.oauth2jwt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.oauth2jwt.domain.likes.LikeRepository;
import com.cos.oauth2jwt.domain.likes.Likes;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeService {
	private final LikeRepository likeRepository;
	
	@Transactional
	public Likes 좋아요(Likes like) {
		Likes likeEntity = likeRepository.save(like);
		return likeEntity;
	}
	
	@Transactional
	public void 좋아요취소(long id) {
		likeRepository.deleteById(id);
	}
}
