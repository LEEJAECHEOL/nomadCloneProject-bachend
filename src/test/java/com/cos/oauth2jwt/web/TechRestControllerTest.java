package com.cos.oauth2jwt.web;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
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

import com.cos.oauth2jwt.domain.community.CommunityRepository;
import com.cos.oauth2jwt.domain.tech.TechRepository;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class TechRestControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private TechRepository techRepository;
	
	@Test
	public void findAll_테스트() throws Exception {
		// when
		ResultActions resultActions = mockMvc.perform(get("/tech")
				.accept(MediaType.APPLICATION_JSON_UTF8));
		
		// 문서에서 mockMvc를 찾아보자
		// then
		resultActions
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print());
	}


}
