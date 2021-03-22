package com.cos.oauth2jwt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cos.oauth2jwt.domain.video.VideoFolder;
import com.cos.oauth2jwt.domain.video.VideoFolderRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VideoFolderService {
	private final VideoFolderRepository videoFolderRepository;
	
	public List<VideoFolder> 전체가져오기(){
		return videoFolderRepository.findAll();
	}
	
	public VideoFolder 저장하기(VideoFolder videoFolder) {
		return videoFolderRepository.save(videoFolder);
	}
	public void 삭제하기(Long id) {
		videoFolderRepository.deleteById(id);
	}
}
