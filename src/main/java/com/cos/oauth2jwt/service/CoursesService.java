package com.cos.oauth2jwt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.oauth2jwt.domain.courses.Courses;
import com.cos.oauth2jwt.domain.courses.CoursesRepository;
import com.cos.oauth2jwt.domain.courses.dto.CoursesPreviewRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CoursesService {
	
	private final CoursesRepository coursesRepository;
	
	public List<CoursesPreviewRespDto> 미리보기전체가져오기(){
		List<Courses> coursesEntity = coursesRepository.findAll();
		List<CoursesPreviewRespDto> previewDto = new ArrayList<>();
		
		coursesEntity.stream().forEach((item)->{
			previewDto.add(
					CoursesPreviewRespDto.builder()
						.id(item.getId())
						.title(item.getTitle())
						.subTitle(item.getSubTitle())
						.level(item.getLevel())
						.previewImage(item.getPreviewImage())
						.build()
					);
		});
		return previewDto;
	}
	
	@Transactional
	public Courses 저장하기(Courses courses) {
		return coursesRepository.save(courses);
	}
	
}
