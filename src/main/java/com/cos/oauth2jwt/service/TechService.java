package com.cos.oauth2jwt.service;

<<<<<<< HEAD
import org.springframework.stereotype.Service;
import com.cos.oauth2jwt.domain.tech.Tech;
import com.cos.oauth2jwt.domain.tech.TechRepositroy;
=======
import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.oauth2jwt.domain.tech.Tech;
import com.cos.oauth2jwt.domain.tech.TechRepository;
import com.cos.oauth2jwt.web.tech.dto.TechSaveReqDto;
>>>>>>> 413aacb08dc45ce3b90edd1775b122ac3ed717a8

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TechService {

<<<<<<< HEAD
	private final TechRepositroy techRepositroy;

	public Tech save(Tech tech) {
		Tech techEntity = techRepositroy.save(tech);
		return techEntity;
=======
	private final TechRepository techRepository;
	
	@Transactional(readOnly = true)
	public List<Tech> 테크전체찾기() {
		return techRepository.findAll();
	}
	
	@Transactional
	public void 테크저장(TechSaveReqDto techSaveReqDto) {
		
		// 이미지 업로드 해야함
		
		Tech tech = techSaveReqDto.toEntity();
		techRepository.save(tech);
	}
	
	
	@Transactional
	public void 테크삭제(long id) {
		techRepository.deleteById(id);
	}
	
	@Transactional
	public void 테크수정(long id, TechSaveReqDto techSaveReqDto) {
		
		// 이미지 업로드 해야함
		
		Tech techEntity = techRepository.findById(id).get();
		techEntity.setName(techSaveReqDto.getName());
>>>>>>> 413aacb08dc45ce3b90edd1775b122ac3ed717a8
	}
}
