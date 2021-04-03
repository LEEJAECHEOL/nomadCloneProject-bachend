package com.cos.oauth2jwt.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cos.oauth2jwt.Query.CommunityQuery;
import com.cos.oauth2jwt.domain.community.Community;
import com.cos.oauth2jwt.domain.community.CommunityRepository;
import com.cos.oauth2jwt.web.community.dto.CommunityListRespDto;
import com.cos.oauth2jwt.web.community.dto.CommunityUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommunityService {

	private final CommunityRepository communityRepository;
	private final CommunityQuery communityQuery;
	
	@Transactional(readOnly = true)
	public List<CommunityListRespDto> 전체찾기(String sort, Long categoryId, Long principalId, Pageable pageable ) {
		return communityQuery.findAllByCategoryAndSort(sort, categoryId, principalId, pageable);
	}

//	@Transactional(readOnly = true)
//	public List<Community> 전체찾기(long principalId) {
//		List<Community> CommuniyEntity = communityRepository.findAll();
//		
//		CommuniyEntity.forEach((community) -> {
//			int likeCount = community.getLikes().size();
//			community.setLikeCount(likeCount);
//			community.getLikes().forEach((like) -> {
//				if (like.getUser().getId() == principalId) {
//					community.setLikeCheck(true);
//				}
//			});
//		});
//
//		return CommuniyEntity;
//	}

	@Transactional(readOnly = true)
	public List<Community> 전체찾기() {
		List<Community> CommuniyEntity = communityRepository.findAll();

		CommuniyEntity.forEach((community) -> {
			int likeCount = community.getLikes().size();
			community.setLikeCount(likeCount);
		});

		return CommuniyEntity;
	}
	
//	@Transactional(readOnly = true)
//	public List<Community> 카테고리로찾기(long id) {
//		List<Community> CommuniyEntity = communityRepository.categoryCommunity(id);
//		return CommuniyEntity;
//	}
//
//	@Transactional(readOnly = true)
//	public List<Community> 카테고리별인기순으로찾기(long id) {
//		List<Community> CommuniyEntity = communityRepository.popularCommunity(id);
//		return CommuniyEntity;
//	}
//
//	@Transactional(readOnly = true)
//	public List<Community> 카테고리별최신순으로찾기(long id) {
//		List<Community> CommuniyEntity = communityRepository.createDateCommunity(id);
//		return CommuniyEntity;
//	}

	@Transactional
	public Community 글저장(Community community) {
		Community communityEntity = communityRepository.save(community); // 실패하면 리턴까지 안가고 Exception이 뜬다.
		return communityEntity;
	}

	@Transactional(readOnly = true) // 쓰는이유 1. 변경감지안함 2. 고립성
	public Community 한건찾기(long id) {
		Community communityEntity = communityRepository.findById(id).get();
		return communityEntity;
	}

	@Transactional
	public Community 수정하기(long id, CommunityUpdateReqDto communityUpdateReqDto) {
		Community communityEntity = communityRepository.findById(id).get();
		communityEntity.setTitle(communityUpdateReqDto.getTitle());
		communityEntity.setContent(communityUpdateReqDto.getContent());
		return communityEntity;
	}

	@Transactional
	public void 삭제하기(long id) {
		communityRepository.deleteById(id);
	}
}