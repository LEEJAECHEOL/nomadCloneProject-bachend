package com.cos.oauth2jwt.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import com.cos.oauth2jwt.domain.faq.FaqRepository;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class FaqRestControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private FaqRepository faqRepository;
	
	@Test
	public void findAll_테스트() throws Exception {
		// when
		ResultActions resultActions = mockMvc.perform(get("/courses")
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		// 문서에서 mockMvc를 찾아보자
		// then
		resultActions
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print());
	}
	@Test
	public void findById_테스트() throws Exception {
		// when
		ResultActions resultActions = mockMvc.perform(get("/courses/1")
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		// 문서에서 mockMvc를 찾아보자
		// then
		resultActions
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void findFilterPreview_테스트() throws Exception {
		// when
		ResultActions resultActions = mockMvc.perform(get("/courses/filter?level=고급&isFree=true&techId=4")
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		// 문서에서 mockMvc를 찾아보자
		// then
		resultActions
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void findSixPreview_테스트() throws Exception {
		// when
		ResultActions resultActions = mockMvc.perform(get("/homeCourses")
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		// 문서에서 mockMvc를 찾아보자
		// then
		resultActions
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print());
	}

}
