package com.cos.oauth2jwt.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.oauth2jwt.domain.video.Video;
import com.cos.oauth2jwt.domain.video.VideoRepository;
import com.cos.oauth2jwt.domain.video.dto.VideoSaveReqDto;
import com.cos.oauth2jwt.domain.video.dto.VideoUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VideoService {
	private final VideoRepository videoRepository;
	
	@Transactional(readOnly = true)
	public List<Video> 전체찾기(){
		List<Video> videoEntity = videoRepository.findAll();
		return videoEntity;
	}
	
	@Transactional(readOnly = true)
	public Video 한건찾기(Long id){
		Video videoEntity = videoRepository.findById(id).get();
		return videoEntity;
	}
	
	@Transactional
	public Video 한건저장(VideoSaveReqDto videoSaveReqDto){
		Video video = videoSaveReqDto.toEntity(); 
		Video videoEntity = videoRepository.save(video);
		return videoEntity;	
	}
	
	@Transactional
	public Video 수정하기(Long id, VideoUpdateReqDto videoUpdateReqDto){ 
		Video videoEntity = videoRepository.findById(id).get(); //영속화
		videoEntity.setTitle(videoUpdateReqDto.getTitle());
		videoEntity.setVimeoId(videoUpdateReqDto.getVimeoId());
		videoEntity.setFree(videoUpdateReqDto.isFree());
		return videoEntity;	
	}
	
	@Transactional
	public void 삭제하기(Long id) {
		videoRepository.deleteById(id);
	}
}
