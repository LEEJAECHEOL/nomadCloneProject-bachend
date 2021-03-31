package com.cos.oauth2jwt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.oauth2jwt.domain.courses.Courses;
import com.cos.oauth2jwt.domain.courses.CoursesRepository;
import com.cos.oauth2jwt.web.courses.dto.CoursesPreviewRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CoursesService {
	
	private final CoursesRepository coursesRepository;
	
	@Transactional(readOnly = true)
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
	
	@Transactional(readOnly = true)
	public List<CoursesPreviewRespDto> 미리보기6개가져오기(){
		List<Courses> coursesEntity = coursesRepository.homeCourses();
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
	
	@Transactional(readOnly = true)
	public Courses 한건가져오기(long id){
		Courses coursesEntity = coursesRepository.findById(id).get();
		return coursesEntity;
	}
	
	@Transactional
	public Courses 저장하기(Courses courses) {
		return coursesRepository.save(courses);
	}
	
	@Transactional
	public void 삭제하기(Long id) {
		coursesRepository.deleteById(id);
	}
	
	@Transactional
	public void 수정하기(Long id, Courses courses) {
		Courses coursesEntity = coursesRepository.findById(id).get();
		coursesEntity.setPreviewImage(courses.getPreviewImage());
		coursesEntity.setMainImage(courses.getMainImage());
		coursesEntity.setTitle(courses.getTitle());
		coursesEntity.setSubTitle(courses.getSubTitle());
		coursesEntity.setBackgroundColor(courses.getBackgroundColor());
		coursesEntity.setTextColor(courses.getTextColor());
		coursesEntity.setLevel(courses.getLevel());
		coursesEntity.setTech(courses.getTech());
		coursesEntity.setVideoInfo(courses.getVideoInfo());
		coursesEntity.setSimpleInfo(courses.getSimpleInfo());
		coursesEntity.setConcept(courses.getConcept());
		coursesEntity.setSkill(courses.getSkill());
		coursesEntity.setLectureAfter(courses.getLectureAfter());
		coursesEntity.setVideo(courses.getVideo());
		coursesEntity.setPrice(courses.getPrice());
	}
}
