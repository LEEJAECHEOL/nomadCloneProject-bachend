package com.cos.oauth2jwt.service;

import org.springframework.stereotype.Service;
import com.cos.oauth2jwt.domain.tech.Tech;
import com.cos.oauth2jwt.domain.tech.TechRepositroy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TechService {

	private final TechRepositroy techRepositroy;

	public Tech save(Tech tech) {
		Tech techEntity = techRepositroy.save(tech);
		return techEntity;
	}
}
