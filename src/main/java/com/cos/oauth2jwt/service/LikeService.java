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
		// 좋아요 누른지 like테이블에서 검사
		Long communityId = like.getCommunity().getId();
		Long userId = like.getUser().getId();
		int exist = likeRepository.findbycommunityIdAndUserId(communityId, userId);
		if (exist > 0) {
			likeRepository.deletebycommunityIdAndUserId(communityId, userId);
			return null;
		}

		// 검사 후 좋아요테이블에 좋아요넣기.
		Likes likeEntity = likeRepository.save(like);
		return likeEntity;
	}
}
