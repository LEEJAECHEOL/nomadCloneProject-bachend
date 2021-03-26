package com.cos.oauth2jwt.web.courses;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.oauth2jwt.domain.courses.Courses;
import com.cos.oauth2jwt.service.CoursesService;
import com.cos.oauth2jwt.web.courses.dto.CoursesPreviewRespDto;
import com.cos.oauth2jwt.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CoursesRestController {
	
	private final CoursesService coursesService;
	
	@GetMapping("/courses")
	public CMRespDto<?> findAllPreview() {
		List<CoursesPreviewRespDto> entity = coursesService.미리보기전체가져오기();
		
		return new CMRespDto<>(HttpStatus.OK.value(), "" , entity);
	}
	
	
	@GetMapping("/homeCourses")
	public CMRespDto<?> findSixPreview() {
		List<CoursesPreviewRespDto> entity = coursesService.미리보기6개가져오기();
		
		return new CMRespDto<>(HttpStatus.OK.value(), "" , entity);
	}
	
	@GetMapping("/courses/{id}")
	public CMRespDto<?> findById(@PathVariable long id) {
		Courses entity = coursesService.한건가져오기(id);
		return new CMRespDto<>(HttpStatus.OK.value(), "" , entity);
	}
	
//	관리자가 등록함.
	@PostMapping("/admin/courses")
	public CMRespDto<?> save(@RequestBody CoursesSaveReqDto coursesSaveReqDto) {
		Courses coursesEntity =  coursesService.저장하기(coursesSaveReqDto.toEntity());
		if(coursesEntity != null){
			return new CMRespDto<>(HttpStatus.CREATED.value(), "" , null);
		}else {
			return new CMRespDto<>(HttpStatus.BAD_REQUEST.value(), "fail" , null);
		}
	}
	
	@DeleteMapping("/courses/{id}")
	public CMRespDto<?> delete(@PathVariable Long id){
		coursesService.삭제하기(id);
		return new CMRespDto<>(HttpStatus.OK.value(), "" , null);
	}
	
	@PutMapping("/courses/{id}")
	public CMRespDto<?> update(@PathVariable Long id, @RequestBody CoursesSaveReqDto coursesSaveReqDto){
		coursesService.수정하기(id, coursesSaveReqDto.toEntity());
		return new CMRespDto<>(HttpStatus.OK.value(), "" , null);
	}
	
}
