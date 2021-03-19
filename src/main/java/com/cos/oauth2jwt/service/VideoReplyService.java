package com.cos.oauth2jwt.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.oauth2jwt.domain.video.Video;
import com.cos.oauth2jwt.domain.video.VideoReply;
import com.cos.oauth2jwt.domain.video.VideoReplyRepository;
import com.cos.oauth2jwt.domain.video.dto.VideoReplySaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VideoReplyService {
	private final VideoReplyRepository videoReplyRepository;
	
	@Transactional
	public VideoReply 한건저장(VideoReplySaveReqDto videoReplySaveReqDto) {
		VideoReply videoReply = videoReplySaveReqDto.toEntity();
		VideoReply videoReplyEntity = videoReplyRepository.save(videoReply);
		return videoReplyEntity;
	}
	@Transactional
	public void 댓글삭제(long id) {
		videoReplyRepository.deleteById(id); // 실패하면 리턴까지 안가고 Exception이 뜬다.
	}

	@Transactional(readOnly = true)
	public List<VideoReply> 전체댓글(){
		List<VideoReply> videoReplys = videoReplyRepository.findAll();
		return videoReplys;
	}

	@Transactional(readOnly = true)
	public VideoReply 한건찾기(long id){
		VideoReply videoReplysEntity = videoReplyRepository.findById(id).get();
		return videoReplysEntity;
	}
}
