package com.cos.oauth2jwt.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.oauth2jwt.domain.tech.Tech;
import com.cos.oauth2jwt.domain.tech.TechRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TechService {

	private final TechRepository techRepository;

	@Transactional
	public Tech 테크저장(Tech tech) {
		Tech techEntity = techRepository.save(tech);
		return techEntity;
	}
	
	@Transactional(readOnly = true)
	public List<Tech> 테크전체찾기() {
		return techRepository.findAll();
	}

	@Transactional
	public void 테크삭제(long id) {
		techRepository.deleteById(id);
	}
}
