package com.cos.oauth2jwt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.oauth2jwt.Query.CommunityQuery;
import com.cos.oauth2jwt.Query.CoursesQuery;
import com.cos.oauth2jwt.domain.courses.Courses;
import com.cos.oauth2jwt.domain.courses.CoursesRepository;
import com.cos.oauth2jwt.handler.exception.NoDataException;
import com.cos.oauth2jwt.web.courses.dto.CoursesFilterPreviewRespDto;
import com.cos.oauth2jwt.web.courses.dto.CoursesPreviewRespDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CoursesService {
	
	private final CoursesRepository coursesRepository;
	private final CoursesQuery coursesQuery;
	
	@Transactional(readOnly = true)
	public Courses 비디오로한건가져오기(long videoId){
		return coursesRepository.findByVideoId(videoId);
	}
	
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
	public List<CoursesPreviewRespDto> 미리보기필터링하기(String level, String isFree, Long techId){
		List<CoursesFilterPreviewRespDto> entity = coursesQuery.findByFilter(level, isFree);
		List<CoursesPreviewRespDto> resp = new ArrayList<>();
		if(techId != 0) {
			entity.stream().forEach((course) -> {
				String tech = course.getTech();
				if(tech.contains("\"techId\": " + techId)) {
					CoursesPreviewRespDto dto = new CoursesPreviewRespDto();
					dto.setId(course.getId().longValue());
					dto.setTitle(course.getTitle());
					dto.setSubTitle(course.getSubTitle());
					dto.setLevel(course.getLevel());
					dto.setVideoId(course.getVideoId().longValue()); // 추가
					Map<String, Object> previewImage = new HashMap<>();
					ObjectMapper objectMapper = new ObjectMapper();
					try {
						previewImage= objectMapper.readValue(course.getPreviewImage(), HashMap.class);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					dto.setPreviewImage(previewImage);
					resp.add(dto);
				}
			});
		}else {
			entity.stream().forEach((course) -> {
				CoursesPreviewRespDto dto = new CoursesPreviewRespDto();
				dto.setId(course.getId().longValue());
				dto.setTitle(course.getTitle());
				dto.setSubTitle(course.getSubTitle());
				dto.setLevel(course.getLevel());
				dto.setVideoId(course.getVideoId().longValue()); 
				Map<String, Object> previewImage = new HashMap<>();
				ObjectMapper objectMapper = new ObjectMapper();
				try {
					previewImage= objectMapper.readValue(course.getPreviewImage(), HashMap.class);
				} catch (Exception e) {
					e.printStackTrace();
				} 
				dto.setPreviewImage(previewImage);
				resp.add(dto);
			});
		}
		return resp;
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
						.videoId(item.getVideo().getId())
						.previewImage(item.getPreviewImage())
						.build()
					);
		});
		return previewDto;
	}
	
	@Transactional(readOnly = true)
	public Courses 한건가져오기(long id){
		Courses coursesEntity = coursesRepository.findById(id).orElseThrow(()->{
			throw new NoDataException("게시물이 존재하지 않습니다.");
		});
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
