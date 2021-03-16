package com.cos.oauth2jwt.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {
	
	@GetMapping("home")
	public String home() {
		return "home";
	}
	
}
