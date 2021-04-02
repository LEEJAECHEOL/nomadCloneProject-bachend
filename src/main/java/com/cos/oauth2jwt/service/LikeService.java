package com.cos.oauth2jwt.service;

import java.util.List;

import javax.persistence.Query;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.oauth2jwt.Query.CommunityQuery;
import com.cos.oauth2jwt.domain.likes.LikeRepository;
import com.cos.oauth2jwt.domain.likes.Likes;
import com.cos.oauth2jwt.web.community.dto.CommunityListRespDto;
import com.cos.oauth2jwt.web.likes.dto.LikeClickRespDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeService {
	private final LikeRepository likeRepository;
	private final CommunityQuery communityQuery;

	@Transactional
	public LikeClickRespDto 좋아요(Likes like) {
		// 좋아요 누른지 like테이블에서 검사
		Long communityId = like.getCommunity().getId();
		Long userId = like.getUser().getId();
		
		int exist = likeRepository.findbycommunityIdAndUserId(communityId, userId);
		
		if (exist > 0) {
			// 존재하면 그냥 삭제.
			likeRepository.deletebycommunityIdAndUserId(communityId, userId);
			LikeClickRespDto respdto = communityQuery.LikeClick(userId, communityId);
			return respdto;
		}

		// 검사 후 좋아요테이블에 좋아요넣기.
		likeRepository.save(like);
		
		LikeClickRespDto respdto = communityQuery.LikeClick(userId, communityId);
		return respdto;
	}
}
