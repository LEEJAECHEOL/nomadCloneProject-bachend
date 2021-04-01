package com.cos.oauth2jwt.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cos.oauth2jwt.domain.video.VideoReply;
import com.cos.oauth2jwt.domain.video.VideoReplyRepository;
import com.cos.oauth2jwt.domain.video.dto.VideoReplyUpdateReqDto;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VideoReplyService {
	private final VideoReplyRepository videoReplyRepository;
	
	@Transactional		//댓글추가
	public VideoReply 한건저장(VideoReply videoReply) {
		VideoReply videoReplyEntity = videoReplyRepository.save(videoReply);
		return videoReplyEntity;
	}
	@Transactional		//댓글삭제
	public void 삭제하기(long id) {
		videoReplyRepository.deleteById(id); // 실패하면 리턴까지 안가고 Exception이 뜬다.
	}
	
	@Transactional		//댓글수정하기
	public VideoReply 수정하기(Long id, VideoReplyUpdateReqDto videoReplyUpdateReqDto){ 
		VideoReply videoReplyEntity = videoReplyRepository.findById(id).get(); //영속화
		videoReplyEntity.setContent(videoReplyUpdateReqDto.getContent());
		return videoReplyEntity;	
	}

	@Transactional(readOnly = true)		//
	public List<VideoReply> 전체찾기(){
		List<VideoReply> videoReplys = videoReplyRepository.findAll();
		return videoReplys;
	}

	@Transactional(readOnly = true)
	public VideoReply 한건찾기(long id){
		VideoReply videoReplysEntity = videoReplyRepository.findById(id).get();
		return videoReplysEntity;
	}
}
