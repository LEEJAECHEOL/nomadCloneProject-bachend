package com.cos.oauth2jwt.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.cos.oauth2jwt.domain.community.Community;
import com.cos.oauth2jwt.domain.community.CommunityRepository;
import com.cos.oauth2jwt.domain.community.dto.CommunitySaveReqDto;
import com.cos.oauth2jwt.domain.community.dto.CommunityUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommunityService {

	private final CommunityRepository communityRepository;
	
	@Transactional(readOnly = true) // 쓰는이유 1. 변경감지안함 2. 고립성
	public List<Community> 전체찾기(){
		List<Community> CommuniyEntity = communityRepository.findAll();
		return CommuniyEntity;
	}
	
	@Transactional
	public Community 글저장(CommunitySaveReqDto communitySaveReqDto) {
		Community community = communitySaveReqDto.toEntity();
		Community communityEntity = communityRepository.save(community); // 실패하면 리턴까지 안가고 Exception이 뜬다.
		return communityEntity;
	}
	
	@Transactional(readOnly = true) // 쓰는이유 1. 변경감지안함 2. 고립성
	public Community 한건찾기(long id) {
		Community communityEntity = communityRepository.findById(id).get();
		return communityEntity;
	}

	@Transactional
	public Community 수정하기(long id,CommunityUpdateReqDto communityUpdateReqDto) {
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
