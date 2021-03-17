package com.cos.oauth2jwt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.oauth2jwt.domain.courses.Courses;
import com.cos.oauth2jwt.domain.courses.CoursesRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CoursesService {
	
	private final CoursesRepository coursesRepository;
	
	@Transactional
	public Courses 저장하기(Courses courses) {
		return coursesRepository.save(courses);
	}
	
}
