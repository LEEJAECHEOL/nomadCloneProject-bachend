package com.cos.oauth2jwt.web.courses;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.oauth2jwt.domain.courses.Courses;
import com.cos.oauth2jwt.domain.courses.dto.CoursesPreviewRespDto;
import com.cos.oauth2jwt.service.CoursesService;import com.cos.oauth2jwt.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CoursesRestController {
	
	private final CoursesService coursesService;
	
	@GetMapping("/courses")
	public CMRespDto<?> findAllPreview() {
		List<CoursesPreviewRespDto> entity = coursesService.미리보기전체가져오기();
		
		return new CMRespDto<>(HttpStatus.CREATED.value(), "success" , entity);
	}
	
//	관리자가 등록함.
	@PostMapping("/courses")
	public CMRespDto<?> save(@RequestBody Courses courses) {
		System.out.println(courses);
		Courses coursesEntity =  coursesService.저장하기(courses);
		if(coursesEntity != null){
			return new CMRespDto<>(HttpStatus.CREATED.value(), "success" , null);
		}else {
			return new CMRespDto<>(HttpStatus.BAD_REQUEST.value(), "fail" , null);
		}
	}
}
